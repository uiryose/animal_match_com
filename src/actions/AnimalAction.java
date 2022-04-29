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

  /*ログインせっしょに保存したため不要？
        //セッションからログイン中のユーザー情報を取得
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);
        //ログイン中のユーザーIDを元に、動物園テーブルから情報を取得
        ZooView zv = zooService.findOneByUserId(uv.getId());

        putRequestScope(AttributeConst.ZOO, zv);
  */

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
        String imageName = zv.getZooName() + rnd.nextInt() + part.getSubmittedFileName();;

        if(checkToken()) {

            //パラメータの値を元にAnimalインスタンスを作成する
            AnimalView av = new AnimalView(
                    null,
                    bv,
                    zv,
                    getRequestParam(AttributeConst.ANI_NICKNAME),
                    getRequestParam(AttributeConst.ANI_IMAGE) != null
                        ? imageName
                        : null,
                    toNumber(getRequestParam(AttributeConst.ANI_AGE)),
                    toNumber(getRequestParam(AttributeConst.ANI_SEX)),
                    toNumber(getRequestParam(AttributeConst.PRICE_FOR_CUST)),
                    toNumber(getRequestParam(AttributeConst.PRICE_FOR_ZOO)),
                    getRequestParam(AttributeConst.ZOO_COMMENT),
                    toNumber(getRequestParam(AttributeConst.PRICE_FOR_CUST)) < 0
                        ? AttributeConst.SOLD_FLAG_TRUE.getIntegerValue()
                        : AttributeConst.SOLD_FLAG_FALSE.getIntegerValue(),
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

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_ZOO, ForwardConst.CMD_INDEX);
            }

        }



    }



}
