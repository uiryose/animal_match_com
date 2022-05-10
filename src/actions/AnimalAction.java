package actions;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import actions.views.AnimalBaseView;
import actions.views.AnimalView;
import actions.views.UserView;
import actions.views.ZooView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import models.Animal;
import services.AnimalBaseService;
import services.AnimalService;
import services.ZooService;

@MultipartConfig(location="C:/tmp", maxFileSize=2097152, maxRequestSize=2097152, fileSizeThreshold=2097152)
public class AnimalAction extends ActionBase {

    private AnimalService animalService;
    private AnimalBaseService animalBaseService;
    private ZooService zooService;

    @Override
    public void process() throws ServletException, IOException {

        animalService = new AnimalService();
        animalBaseService = new AnimalBaseService();
        zooService = new ZooService();

        //メソッドの実行
        invoke();

        animalService.close();
        animalBaseService.close();
        zooService.close();
    }


    /**
     * 動物新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException{

        //基本動物情報を全て取得し、リクエストスコープに保存する
        putRequestScope(AttributeConst.ANIMALBASES, animalBaseService.getAllOrderByName());

        putRequestScope(AttributeConst.TOKEN, getTokenId());
        putRequestScope(AttributeConst.ANIMAL, new AnimalView());

        //動物新規登録画面を表示
        forward(ForwardConst.FW_ANI_NEW);
    }


    /**
     * 動物情報の新規登録処理を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //セッションスコープからログイン中のUserを取得する
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);
        //ログイン中のユーザーIDを元に、動物園テーブルから情報を取得
        ZooView zv = zooService.findOneByUserId(uv.getId());

        //パラメータで取得した基本動物情報を元にデータを取得する
        Integer baseId = toNumber(getRequestParam(AttributeConst.BASE_ID));
        AnimalBaseView bv = animalBaseService.findOne(baseId);

        //name属性がANI_IMAGEのファイルをPartオブジェクトとして取得
        Part part = request.getPart(AttributeConst.ANI_IMAGE.getValue());
        //画像の名前を重複なく作成する（動物園名＋乱数＋ファイル名とする）
        Random rnd = new Random();
        String imageName = zv.getZooName() + rnd.nextInt() + part.getSubmittedFileName();


        System.out.println("テスト販売PART：" +  part);
        System.out.println("テスト販売PARTgetSub：" +  part.getSubmittedFileName());
        System.out.println("テスト販売フラグ：" + AttributeConst.SOLD_FLAG_FALSE.getIntegerValue());


        if(checkToken()) {

            //パラメータの値を元にAnimalインスタンスを作成する
            AnimalView av = new AnimalView(
                    null,
                    bv,
                    zv,
                    getRequestParam(AttributeConst.ANI_NICKNAME),

                    !part.getSubmittedFileName().equals("")
                             ? imageName
                             : null,

//                    getRequestParam(AttributeConst.ANI_IMAGE) != null
//                        ? imageName
//                        : null,
                    toNumber(getRequestParam(AttributeConst.ANI_AGE)),
                    toNumber(getRequestParam(AttributeConst.ANI_SEX)),
                    bv.getBaseBreedFlag() == AttributeConst.BREED_FLAG_FALSE.getIntegerValue()
                            ? -1   //個人飼育不可の動物は、個人向け価格-1円とする
                            : toNumber(getRequestParam(AttributeConst.PRICE_FOR_CUST)),
                    toNumber(getRequestParam(AttributeConst.PRICE_FOR_ZOO)),
                    getRequestParam(AttributeConst.ZOO_COMMENT),
                    AttributeConst.SOLD_FLAG_FALSE.getIntegerValue(),
                    null,
                    null);

            //動物情報の登録
            List<String> errors = animalService.create(av);

            if(errors.size() > 0) {

                //基本動物情報を全て取得し、リクエストスコープに保存する
                putRequestScope(AttributeConst.ANIMALBASES, animalBaseService.getAllOrderByName());

                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.ANIMAL, av);
                putRequestScope(AttributeConst.ZOO, zv);
                putRequestScope(AttributeConst.ERR, errors);

                //動物新規登録画面を再表示
                forward(ForwardConst.FW_ANI_NEW);
            } else {
                //エラーがなかった場合

                //画像を保存する処理を行う
                if(part != null) {
                    //フォルダに画像の書き込み
                    part.write(context.getRealPath("/image/animal") + "/" + imageName);
                }
                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //販売中の動物一覧画面にリダイレクト
                redirect(ForwardConst.ACT_ANI, ForwardConst.CMD_SELLING);
            }
        }
    }


    /**
     * 動物園マイページの販売中動物の一覧ページを表示する
     * @throws ServletException
     * @throws IOException
     */
    public void showSelling() throws ServletException, IOException {

        //ログインしている動物園のidを元に。販売中の動物一覧と件数を取得する
        ZooView zv = (ZooView) getSessionScope(AttributeConst.LOGIN_ZOO);
        List<AnimalView> animals =  animalService.getMySelling(zv);
        Long animalCount = animalService.countMySelling(zv);

        putRequestScope(AttributeConst.ANIMALS, animals);
        putRequestScope(AttributeConst.ANI_SELLING_COUNT, animalCount);

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //販売中一覧画面を表示
        forward(ForwardConst.FW_ZOO_SELLING);
    }


    /**
     * 動物園マイページの販売済み動物の一覧ページを表示する
     * @throws ServletException
     * @throws IOException
     */
    public void showSold() throws ServletException, IOException {

        //ログインしている動物園のidを元に。販売済みの動物一覧と件数を取得する
        ZooView zv = (ZooView) getSessionScope(AttributeConst.LOGIN_ZOO);
        List<AnimalView> animals =  animalService.getMySold(zv);
        Long animalCount = animalService.countMySold(zv);

        putRequestScope(AttributeConst.ANIMALS, animals);
        putRequestScope(AttributeConst.ANI_SOLD_COUNT, animalCount);

        //販売中一覧画面を表示
        forward(ForwardConst.FW_ZOO_SOLD);
    }


    /**
     * 動物の詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

        //パラメータのidを元に、動物データを取得する
        AnimalView av = animalService.findOne(toNumber(getRequestParam(AttributeConst.ANI_ID)));
        putRequestScope(AttributeConst.ANIMAL, av);

        if(av == null ) {

            forward(ForwardConst.FW_ERR_UNKNOWN);
        } else {

            //動物詳細画面を表示する
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            forward(ForwardConst.FW_ANI_SHOW);
        }
    }


    /**
     * 動物の修正画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

        //パラメータのidを元に、動物データを取得する
        AnimalView animalView = animalService.findOne(toNumber(getRequestParam(AttributeConst.ANI_ID)));
        putRequestScope(AttributeConst.ANIMAL, animalView);

        //セッションからログイン中の動物園情報を取得する
        ZooView zoo = (ZooView) getSessionScope(AttributeConst.LOGIN_ZOO);

        if(animalView == null || animalView.getZoo().getId() != zoo.getId()) {
            //該当の動物データが存在しない、または
            //ログインしている動物園と動物登録者が一致しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.ANIMAL, animalView);

            //編集画面を表示
            forward(ForwardConst.FW_ANI_EDIT);
        }
    }


    /**
     * 動物の更新処理を行う
     * @throws ServletException
     * @throws IOException
     */
    public void update() throws ServletException, IOException {

        if(checkToken()) {

            //パラメータのidを元に、動物データを取得する
            AnimalView av = animalService.findOne(toNumber(getRequestParam(AttributeConst.ANI_ID)));

            //name属性がANI_IMAGEのファイルをPartオブジェクトとして取得
            Part part = request.getPart(AttributeConst.ANI_IMAGE.getValue());

            //画像の名前を重複なく作成する（動物園名＋乱数＋ファイル名とする）
            Random rnd = new Random();
            String imageName = av.getZoo().getZooName() + rnd.nextInt() + part.getSubmittedFileName();

            //画面入力されてた内容を上書きする
            av.setNickname(getRequestParam(AttributeConst.ANI_NICKNAME));
            av.setAnimalAge(toNumber(getRequestParam(AttributeConst.ANI_AGE)));
            av.setAnimalSex(toNumber(getRequestParam(AttributeConst.ANI_SEX)));
            av.setPriceForCust(toNumber(getRequestParam(AttributeConst.PRICE_FOR_CUST)));
            av.setPriceForZoo(toNumber(getRequestParam(AttributeConst.PRICE_FOR_ZOO)));
            av.setAnimalComment(getRequestParam(AttributeConst.ZOO_COMMENT));
            if(!part.getSubmittedFileName().equals("")) {
                av.setAnimalImage(imageName);
            }

            //動物の更新処理を行う
            List<String> errors = animalService.update(av);

            if(errors.size() > 0) {

                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.ANIMAL, av); //入力された動物情報
                putRequestScope(AttributeConst.ERR, errors);

                //編集画面を再表示
                forward(ForwardConst.FW_ANI_EDIT);
            } else {
                //更新中にエラーがなかった場合
                //フォルダに画像の書き込み
                part.write(context.getRealPath("/image/animal") + "/" + imageName);

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //詳細確認画面にリダイレクト
                redirect(ForwardConst.ACT_ANI, ForwardConst.CMD_SHOW, av.getId());
            }
        }
    }


    /**
     * 動物の販売済みフラグを変更する
     * @throws ServletException
     * @throws IOException
     */
    public void sold() throws ServletException, IOException {

        //販売済にする動物データを取得する
        AnimalView av = animalService.findOne(toNumber(getRequestParam(AttributeConst.ANI_ID)));
        UserView uv = getSessionScope(AttributeConst.LOGIN_USER);

        if(uv.getId() != av.getZoo().getUser().getId() || av == null) {
            //自分の掲載した動物でなければ、エラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            //フラグを販売済にして、DB情報を更新する
            av.setSoldFlag(AttributeConst.SOLD_FLAG_TRUE.getIntegerValue());

            //動物の更新処理を行う
            animalService.update(av);

            //販売動物一覧に移動する
            redirect(ForwardConst.ACT_ANI, ForwardConst.CMD_SELLING);
        }
    }


    /**
     * 動物を1件削除する
     * @throws ServletException
     * @throws IOException
     */
    public void destroy() throws ServletException, IOException {

        //削除する動物データを取得する
        Animal a = animalService.findOneModel(toNumber(getRequestParam(AttributeConst.ANI_ID)));

        if(a == null) {
            forward(ForwardConst.FW_ERR_UNKNOWN);
            return;
        }

        //DBから動物情報を削除する
        animalService.destory(a);

        //販売動物一覧に移動する
        redirect(ForwardConst.ACT_ANI, ForwardConst.CMD_SELLING);
    }


}
