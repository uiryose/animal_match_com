package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.CustomerView;
import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import constants.PropertyConst;
import services.CustomerService;
import services.UserService;

public class CustomerAction extends ActionBase {

    private CustomerService customerService;
    private UserService userService;


    @Override
    public void process() throws ServletException, IOException {

        customerService = new CustomerService();
        userService = new UserService();

        //メソッドの実行
        invoke();

        customerService.close();
        userService.close();
    }


    /**
     * 顧客ページのトップ画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException{

        //セッションスコープからログイン中のUserを取得する
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);
        //ログイン中のユーザーIDを元に、顧客テーブルから情報を取得
        CustomerView cv = customerService.findOneByUserId(uv.getId());
        putRequestScope(AttributeConst.CUSTOMER, cv);

        //チャット中の動物情報を取得する
        //リクエストスコープに保存する。

        if(uv != null && uv.getUserFlag() == AttributeConst.USER_CUST.getIntegerValue()) {
            //顧客マイページ画面を表示
            forward(ForwardConst.FW_CUST_INDEX);

        } else {
            forward(ForwardConst.FW_ERR_UNKNOWN);
        }
    }


    /**
     * 顧客向けの案内ページを表示する。顧客の新規作成画面を兼ねる
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException{

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        putRequestScope(AttributeConst.USER, new UserView()); //空のUserインスタンス
        putRequestScope(AttributeConst.CUSTOMER, new CustomerView()); //空のCustomerインスタンス

        //案内ページを表示
        forward(ForwardConst.FW_CUST_NEW);
    }


    /**
     * ユーザーと顧客の新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //パラメータの値を元に、UserインスタンスとCustomerインスタンスを作成する
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

            CustomerView cv = new CustomerView(
                    null,
                    null,
                    getRequestParam(AttributeConst.CUST_NAME),
                    null,
                    null);

            //User情報の登録
            List<String> errors = userService.create(uv, pepper, cv);

            if (errors.size() > 0) {

                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.USER, uv);
                putRequestScope(AttributeConst.CUSTOMER, cv);
                putRequestScope(AttributeConst.ERR, errors);

                //新規登録画面を再表示
                forward(ForwardConst.FW_CUST_NEW);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());
System.out.println("テストクリエイト成功1:"+uv.getCode());
System.out.println("テストクリエイト成功2:"+uv.getId());
System.out.println("テストクリエイト成功3:"+cv.getCustomerName());
        //作成したユーザー情報をログイン状態としてセッションスコープに保存する
                putSessionScope(AttributeConst.LOGIN_USER, uv);

                //顧客マイページにリダイレクト
                redirect(ForwardConst.ACT_CUST, ForwardConst.CMD_INDEX);
            }
        }
    }


    /**
     * 顧客情報の変更画面を表示する
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
            //ログイン中のユーザーIDを元に、顧客テーブルから情報を取得
            CustomerView cv = customerService.findOneByUserId(uv.getId());

            if (cv == null) {
                forward(ForwardConst.FW_ERR_UNKNOWN);
                return;

            } else {

                //編集画面に反映させるためリクエストスコープに保存
                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.USER, uv); //取得したユーザー情報
                putRequestScope(AttributeConst.CUSTOMER, cv); //取得した顧客情報

                //編集画面を表示する
                forward(ForwardConst.FW_CUST_EDIT);
            }
        }
    }


    /**
     * ユーザーと顧客情報を更新する
     * @throws ServletException
     * @throws IOException
     */
    public void update() throws ServletException, IOException {

        if (checkToken()) {

            //idを条件にログインしているユーザーの顧客情報を取得
            UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);
            CustomerView cv = customerService.findOneByUserId(uv.getId());

            //パラメータから情報を取得し、
            uv.setCode(getRequestParam(AttributeConst.USER_CODE));
            uv.setPassword(getRequestParam(AttributeConst.USER_PASSWORD));
            cv.setCustomerName(getRequestParam(AttributeConst.CUST_NAME));

            String pepper = getContextScope(PropertyConst.PEPPER);
//ペッパー取得エラー
if (pepper == null) {
    pepper = "test";
}
            List<String> errors = userService.update(uv, pepper, cv);

            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                //入力フォームとエラー内容をリクエストスコープに保存する
                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.USER, uv);
                putRequestScope(AttributeConst.CUSTOMER, cv);
                putRequestScope(AttributeConst.ERR, errors);

                //編集画面を再表示する
                forward(ForwardConst.FW_CUST_EDIT);

            } else {
                //更新中にエラーがなかった場合

                //セッションスコープに更新完了のメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //顧客のマイページにリダイレクト
                redirect(ForwardConst.ACT_CUST, ForwardConst.CMD_INDEX);
            }
        }
    }

    /**
     * 顧客の論理削除を行う
     * @throws ServletException
     * @throws IOException
     */
    public void destroy() throws ServletException, IOException {

        if(checkToken()) {

            //Userのidを条件に顧客データを論理削除する
            userService.destroy(toNumber(getRequestParam(AttributeConst.USER_ID)));

            //セッションに削除完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

            //顧客新規登録画面にリダイレクト
            redirect(ForwardConst.ACT_CUST, ForwardConst.CMD_NEW);

        }
    }

}
