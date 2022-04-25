package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.UserView;
import constants.AttributeConst;

public class UserAction extends ActionBase {


    @Override
    public void process() throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ

    }

    public void index() throws ServletException, IOException{

        //セッションスコープからログイン中のUserを取得する
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

        //チャット中の動物情報を取得する
        //リクエストスコープに保存する。

        if(uv != null) {

        }


    }



}
