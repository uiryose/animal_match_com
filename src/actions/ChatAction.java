package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.ChatView;
import actions.views.CommentView;
import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import services.AnimalBaseService;
import services.AnimalService;
import services.ChatService;
import services.CommentService;
import services.UserService;

public class ChatAction extends ActionBase {

    private AnimalBaseService animalBaseService;
    private AnimalService animalService;
    private UserService userService;
    private ChatService chatService;
    private CommentService commentService;

    @Override
    public void process() throws ServletException, IOException {

        animalBaseService = new AnimalBaseService();
        animalService = new AnimalService();
        userService = new UserService();
        chatService = new ChatService();
        commentService = new CommentService();

        invoke();

        animalBaseService.close();
        animalService.close();
        userService.close();
        chatService.close();
        commentService.close();
    }

    /**
     * チャット画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //リクエストパラメータの動物idから動物データを取得して保存する。
        Integer animalId = toNumber(getRequestParam(AttributeConst.ANI_ID));
        putRequestScope(AttributeConst.ANIMAL, animalService.findOne(animalId));

        //セッション情報からログイン中のユーザーIDを取得
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);
        Integer myId = uv.getId();

        //リクエストスコープからチャット相手のユーザーIDを取得
        Integer companionId = toNumber(getRequestParam(AttributeConst.CHAT_WITH));

        //チャットコメント情報を取得する
        List<CommentView> comments = commentService.getAllComment(animalId, myId, companionId);
        putRequestScope(AttributeConst.COMMENTS, comments);

        //チャット画面を表示する
        putRequestScope(AttributeConst.TOKEN, getTokenId());
        forward(ForwardConst.FW_CHAT_INDEX);

    }

    /**
     * チャットを送信する
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //リクエストパラメータから動物のIDを取得
        Integer animalId = toNumber(getRequestParam(AttributeConst.ANI_ID));
        //リクエストパラメータから動物を掲載した動物園のUserIDを取得
        Integer companionId = toNumber(getRequestParam(AttributeConst.CHAT_WITH));

        //セッションスコープからログイン中のUserIDを取得する
        UserView loginUser = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

        if (checkToken()) {

            //チャットを行うユーザーの組み合わせをチェックして取得する
            ChatView cv = chatService.findOneByOurID(loginUser.getId(), companionId);

            if (cv == null) {
                //組み合わせが存在しなかった場合

                //パラメータの値を元にChatインスタンスを２パターン(送信元、送信先を相互に)作成する。
                ChatView cv1 = new ChatView(
                        null,
                        loginUser,
                        userService.findOne(companionId));
                ChatView cv2 = new ChatView(
                        null,
                        userService.findOne(companionId),
                        loginUser);
                cv = chatService.create(cv1, cv2);
            }

            //パラメータの値を元にCommentインスタンスを作成する
            CommentView commentView = new CommentView(
                    null,
                    animalService.findOne(animalId),
                    cv,
                    getRequestParam(AttributeConst.CHAT_CONTENT),
                    AttributeConst.EDIT_FLAG_FALSE.getIntegerValue(), //未編集:0
                    null,
                    null);

            List<String> errors = commentService.create(commentView);

            if (errors.size() > 0) {

                putRequestScope(AttributeConst.ERR, errors);
                System.out.println("テスト：コメント作成でエラーがありました。");
            }
            //チャット画面を再表示する
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            redirect(ForwardConst.ACT_CHAT, ForwardConst.CMD_INDEX, animalId, companionId);
        }

    }

}
