package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.UserView;
import actions.views.ZooView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import constants.PropertyConst;
import services.UserService;
import services.ZooService;

public class ZooAction extends ActionBase {

    private ZooService zooService;
    private UserService userService;


    @Override
    public void process() throws ServletException, IOException {

        zooService = new ZooService();
        userService = new UserService();

        //メソッドの実行
        invoke();

        zooService.close();
        userService.close();
    }


    /**
     * 動物園専用ページのトップ画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException{

        //セッションスコープからログイン中のUserを取得する
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);
        //ログイン中のユーザーIDを元に、動物園テーブルから情報を取得
        ZooView zv = zooService.findOneByUserId(uv.getId());
        putRequestScope(AttributeConst.ZOO, zv);

        //チャット中の動物情報を取得する
        //リクエストスコープに保存する。

        if(uv != null && uv.getUserFlag() == AttributeConst.USER_ZOO.getIntegerValue()) {
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

            System.out.println("PEPPEのテスト:"+ pepper);
            if (pepper == null) {
                pepper = "test";
            }

            ZooView zv = new ZooView(
                    null,
                    null,
                    getRequestParam(AttributeConst.ZOO_NAME),
                    getRequestParam(AttributeConst.ZOO_REGION),
                    getRequestParam(AttributeConst.ZOO_PHONE),
                    null,
                    null);

            //User情報の登録
            List<String> errors = userService.create(uv, pepper, zv);

            if (errors.size() > 0) {

                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.USER, uv);
                putRequestScope(AttributeConst.ZOO, zv);
                putRequestScope(AttributeConst.ERR, errors);

                //新規登録画面を再表示
                forward(ForwardConst.FW_ZOO_NEW);

            } else {
                //登録中にエラーがなかった場合

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













}
