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

        //メソッドの実行
        invoke();

        zooService.close();
    }



    /**
     * 動物園向けの案内ページを表示する。動物園の新規作成画面を兼ねる
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException{

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
//        putRequestScope(AttributeConst.USER, new UserView()); //空のUserインスタンス
//        putRequestScope(AttributeConst.ZOO, new ZooView()); //空のZooインスタンス

        //案内ページを表示
        forward(ForwardConst.FW_ZOO_NEW);
    }


    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException{

        //CSRF対策 tokenのチェック
        if(checkToken()) {

        }



        //パラメータの値を元に、UserインスタンスとZooインスタンスを作成する
        UserView uv = new UserView(
                null,
                getRequestParam(AttributeConst.USER_CODE),
                toNumber(getRequestParam(AttributeConst.USER_FLAG)),
                getRequestParam(AttributeConst.USER_PASSWORD),
                AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

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
        List<String> errors = userService.create(uv, pepper);
        List<String> zooErrors = zooService.create(zv);

        if(errors.size() > 0) {

            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.USER, uv);
            putRequestScope(AttributeConst.ZOO, zv);

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
