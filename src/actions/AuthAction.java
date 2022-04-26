package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import constants.PropertyConst;
import services.UserService;

public class AuthAction extends ActionBase {

    private UserService userService;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        userService = new UserService();

        invoke();

        userService.close();

    }

    /**
    * 顧客・動物園共通のログイン画面を表示する
    * @throws ServletException
    * @throws IOException
    */
    public void showLogin() throws ServletException, IOException {

        //CSRF対策用トークンの設定
        putRequestScope(AttributeConst.TOKEN, getTokenId());

        //セッションにフラッシュメッセージ(ログイン失敗のメッセージ)が登録されている場合はリクエストスコープに保存する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
            System.out.println("テストshowLoginフラッシュ:" + flush);
        }

        //顧客・動物園共通のログイン画面を表示
        forward(ForwardConst.FW_LOGIN);
    }



    /**
     * ユーザーのログイン処理を行う
     * @throws ServletException
     * @throws IOException
     */
    public void login() throws ServletException, IOException {

        //ログインフォームから情報を取得
        String code = getRequestParam(AttributeConst.USER_CODE);
        String plainPass = getRequestParam(AttributeConst.USER_PASSWORD);
        String pepper = getContextScope(PropertyConst.PEPPER);

        //有効なユーザーか認証する
        Boolean isValidUser = userService.validateLogin(code, plainPass, pepper);

        if (isValidUser) {

            if (checkToken()) {

                //ログインしたユーザーのDBデータを取得
                UserView uv = userService.findOne(code, plainPass, pepper);
                //セッションにログインしたユーザーを設定
                putSessionScope(AttributeConst.LOGIN_USER, uv);

                //マイペページへリダイレクト。ユーザーフラグで顧客ページか動物園専用ページに分岐する
                Integer userFlag = uv.getUserFlag();
                if (userFlag == AttributeConst.USER_CUST.getIntegerValue()) {

                    //顧客のマイページにリダイレクト
                    redirect(ForwardConst.ACT_CUST, ForwardConst.CMD_INDEX);

                } else if (userFlag == AttributeConst.USER_ZOO.getIntegerValue()) {

                    //動物園のマイページにリダイレクト
                    redirect(ForwardConst.ACT_ZOO, ForwardConst.CMD_INDEX);
                }
            }

        } else {
            //認証失敗の場合

            //CSRF対策用トークンを設定
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            //認証失敗エラーメッセージ表示フラグをたてる
            putRequestScope(AttributeConst.LOGIN_ERR, true);

            //顧客・動物園共通のログイン画面を再表示
            forward(ForwardConst.FW_LOGIN);

        }
    }


    /**
     * ユーザーのログアウト処理を行う
     * @throws ServletException
     * @throws IOException
     */
    public void logout() throws ServletException, IOException {

        //セッションからログインユーザーのパラメータを削除
        removeSessionScope(AttributeConst.LOGIN_USER);

        //セッションにログアウト時のフラッシュメッセージを追加
        putSessionScope(AttributeConst.FLUSH, MessageConst.I_LOGOUT.getMessage());

        //顧客・動物園共通のログイン画面にリダイレクト
        redirect(ForwardConst.ACT_AUTH, ForwardConst.CMD_SHOW_LOGIN);

    }


}
