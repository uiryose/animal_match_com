package actions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import actions.views.UserView;
import actions.views.ZooView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import constants.PropertyConst;
import services.AnimalService;
import services.UserService;
import services.ZooService;

public class ZooAction extends ActionBase {

    private ZooService zooService;
    private UserService userService;
    private AnimalService animalService;


    @Override
    public void process() throws ServletException, IOException {

        zooService = new ZooService();
        userService = new UserService();
        animalService = new AnimalService();

        //メソッドの実行
        invoke();

        zooService.close();
        userService.close();
        animalService.close();
    }


    /**
     * 動物園専用ページのトップ画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException{

        //セッションスコープからログイン中のZooを取得する
        ZooView zv = (ZooView) getSessionScope(AttributeConst.LOGIN_ZOO);
        //動物園ごとの動物件数を取得しリクエストスコープに保存する
        Long animalSellingCount = animalService.countMySelling(zv);
        Long animalSoldCount = animalService.countMySold(zv);
        putRequestScope(AttributeConst.ANI_SELLING_COUNT, animalSellingCount);
        putRequestScope(AttributeConst.ANI_SOLD_COUNT, animalSoldCount);

        //チャット中の動物情報を取得する
        //リクエストスコープに保存する。

        if(zv != null) {
            //動物園専用ページ画面を表示
            forward(ForwardConst.FW_ZOO_INDEX);

        } else {
            forward(ForwardConst.FW_ERR_UNKNOWN);
        }
    }

    /**
     * 動物園向けの案内ページを表示する。動物園の新規作成画面を兼ねる
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException{

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        putRequestScope(AttributeConst.USER, new UserView()); //空のUserインスタンス
        putRequestScope(AttributeConst.ZOO, new ZooView()); //空のZooインスタンス

        //案内ページを表示
        forward(ForwardConst.FW_ZOO_NEW);
    }


    /**
     * ユーザーと動物園の新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //パラメータの値を元に、UserインスタンスとZooインスタンスを作成する
            UserView uv = new UserView(
                    null,
                    getRequestParam(AttributeConst.USER_CODE),
                    toNumber(getRequestParam(AttributeConst.USER_FLAG)),
                    getRequestParam(AttributeConst.USER_PASSWORD),
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

            //アプリケーションスコープからpepper文字列を取得
            String pepper = getContextScope(PropertyConst.PEPPER);

            ZooView zv = new ZooView(
                    null,
                    null,
                    getRequestParam(AttributeConst.ZOO_NAME),
                    getRequestParam(AttributeConst.ZOO_REGION),
                    getRequestParam(AttributeConst.ZOO_PHONE),
                    null,
                    null);

            //User情報の登録
            Map<Integer, Object> createdUser = userService.create(uv, pepper, zv);

            @SuppressWarnings("unchecked")
            List<String> errors = (List<String>) createdUser.get(2);

            if (errors.size() > 0) {

                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.USER, uv);
                putRequestScope(AttributeConst.ZOO, zv);
                putRequestScope(AttributeConst.ERR, errors);

                //新規登録画面を再表示
                forward(ForwardConst.FW_ZOO_NEW);

            } else {
                //登録中にエラーがなかった場合

                //新規作成したユーザーのセッションスコープに保存する
                UserView createdUv = userService.findOne((int) createdUser.get(1));
                putSessionScope(AttributeConst.LOGIN_USER, createdUv);
                //ログイン中のユーザーIDを元に、動物園テーブルから情報を取得しセッションスコープに保存する
                putSessionScope(AttributeConst.LOGIN_ZOO, zooService.findOneByUserId(createdUv.getId()));

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_ZOO, ForwardConst.CMD_INDEX);
            }
        }
    }


    /**
     * 動物園情報の変更画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {
        //セッションからログイン中のユーザー情報を取得
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

        if (uv == null || uv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {
            //データが取得できなかった、または論理削除されている場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
            return;

        } else {
            //ログイン中のユーザーIDを元に、動物園テーブルから情報を取得
            ZooView zv = zooService.findOneByUserId(uv.getId());

            if (zv == null) {
                forward(ForwardConst.FW_ERR_UNKNOWN);
                return;

            } else {

                //編集画面に反映させるためリクエストスコープに保存
                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.USER, uv); //取得したユーザー情報
                putRequestScope(AttributeConst.ZOO, zv); //取得した動物園情報

                //編集画面を表示する
                forward(ForwardConst.FW_ZOO_EDIT);
            }
        }
    }


    /**
     * ユーザーと動物園情報を更新する
     * @throws ServletException
     * @throws IOException
     */
    public void update() throws ServletException, IOException {

        if (checkToken()) {

            //idを条件にログインしているユーザーの顧客情報を取得
            UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);
            ZooView zv = zooService.findOneByUserId(uv.getId());

            //パラメータから情報を取得し、
            uv.setCode(getRequestParam(AttributeConst.USER_CODE));
            uv.setPassword(getRequestParam(AttributeConst.USER_PASSWORD));
            zv.setZooName(getRequestParam(AttributeConst.ZOO_NAME));
            zv.setRegion(getRequestParam(AttributeConst.ZOO_REGION));
            zv.setPhone(getRequestParam(AttributeConst.ZOO_PHONE));

            String pepper = getContextScope(PropertyConst.PEPPER);
            List<String> errors = userService.update(uv, pepper, zv);

            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                //入力フォームとエラー内容をリクエストスコープに保存する
                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.USER, uv);
                putRequestScope(AttributeConst.ZOO, zv);
                putRequestScope(AttributeConst.ERR, errors);

                //編集画面を再表示する
                forward(ForwardConst.FW_ZOO_EDIT);

            } else {
                //更新中にエラーがなかった場合

                //ログインセッション情報の更新
                putSessionScope(AttributeConst.LOGIN_USER, uv);
                putSessionScope(AttributeConst.LOGIN_ZOO, zv);

                //セッションスコープに更新完了のメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //動物園のマイページにリダイレクト
                redirect(ForwardConst.ACT_ZOO, ForwardConst.CMD_INDEX);
            }
        }
    }


    /**
     * 動物園の論理削除を行う
     * @throws ServletException
     * @throws IOException
     */
    public void destroy() throws ServletException, IOException {

        if(checkToken()) {

            //Userのidを条件に動物園データを論理削除する
            userService.destroy(toNumber(getRequestParam(AttributeConst.USER_ID)));

            //セッションに削除完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

            //動物園新規登録画面にリダイレクト
            redirect(ForwardConst.ACT_ZOO, ForwardConst.CMD_NEW);

        }
    }








}
