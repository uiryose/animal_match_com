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

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_BASE, ForwardConst.CMD_INDEX);
            }
        }
    }





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





}
