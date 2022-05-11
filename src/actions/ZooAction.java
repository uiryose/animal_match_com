package actions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import actions.views.ChatView;
import actions.views.CommentView;
import actions.views.CustomerView;
import actions.views.UserView;
import actions.views.ZooView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import constants.PropertyConst;
import models.Comment;
import services.AnimalService;
import services.ChatService;
import services.CommentService;
import services.CustomerService;
import services.UserService;
import services.ZooService;

public class ZooAction extends ActionBase {

    private ZooService zooService;
    private CustomerService customerService;
    private UserService userService;
    private AnimalService animalService;
    private CommentService commentService;
    private ChatService chatService;


    @Override
    public void process() throws ServletException, IOException {

        zooService = new ZooService();
        customerService = new CustomerService();
        userService = new UserService();
        animalService = new AnimalService();
        commentService = new CommentService();
        chatService = new ChatService();

        //メソッドの実行
        invoke();

        zooService.close();
        customerService.close();
        userService.close();
        animalService.close();
        commentService.close();
        chatService.close();

    }


    /**
     * 動物園専用ページのトップ画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException{

        //セッションスコープからログイン中のUserとZooを取得する
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);
        ZooView zv = (ZooView) getSessionScope(AttributeConst.LOGIN_ZOO);
        //販売中、販売済の動物件数を取得しリクエストスコープに保存する
        putRequestScope(AttributeConst.ANI_SELLING_COUNT, animalService.countMySelling(zv));
        putRequestScope(AttributeConst.ANI_SOLD_COUNT, animalService.countMySold(zv));

        //チャット中の動物情報を取得し、リクエストスコープに保存する
        List<Object[]> trades = commentService.getZooIndex(uv.getId());
        putRequestScope(AttributeConst.COMMENT_TRADES, trades);

        //取引中の件数をリクエストスコープに保存する
        int tradesCount = 0;
        if(trades.size() > 0) {
            tradesCount = trades.size();
        }
        putRequestScope(AttributeConst.TRADES_COUNT, tradesCount);

        List<CustomerView> custs = customerService.getAll();
        List<ZooView> zoos = zooService.getAll();
        putRequestScope(AttributeConst.CUSTOMERS, custs);
        putRequestScope(AttributeConst.ZOOS, zoos);

        if(zv == null || uv.getUserFlag() != AttributeConst.USER_ZOO.getIntegerValue()) {

            forward(ForwardConst.FW_ERR_UNKNOWN);
        } else {
            //動物園専用ページ画面を表示
            forward(ForwardConst.FW_ZOO_INDEX);
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


    /**
     * 顧客または購入希望の動物園とのチャット画面を開く
     * @throws ServletException
     * @throws IOException
     */
    public void tradeIndex() throws ServletException, IOException {

        //リクエストパラメータの動物idから動物データを取得して保存する。
        Integer animalId = toNumber(getRequestParam(AttributeConst.ANI_ID));
        putRequestScope(AttributeConst.ANIMAL, animalService.findOne(animalId));

        //セッション情報からログイン中のユーザーIDを取得
        UserView loginUser = (UserView) getSessionScope(AttributeConst.LOGIN_USER);
        Integer myId = loginUser.getId();

        //リクエストスコープからチャット相手のユーザーIDを取得    **動物を購入希望の顧客または動物園ID
        Integer buyUserId = toNumber(getRequestParam(AttributeConst.CHAT_WITH));
        putRequestScope(AttributeConst.BUY_USER_ID, buyUserId);

        //チャットコメント情報を取得する
        List<CommentView> comments = commentService.getAllComment(animalId, buyUserId, myId);
        putRequestScope(AttributeConst.COMMENTS, comments);

        //チャット画面を表示する
        putRequestScope(AttributeConst.TOKEN, getTokenId());
        forward(ForwardConst.FW_ZOO_TRADE_INDEX);
    }


    /**
     * 顧客または購入希望の動物園とのチャットコメントを送信する
     * @throws ServletException
     * @throws IOException
     */
    public void tradeCreate() throws ServletException, IOException {

        //リクエストパラメータから動物のIDを取得
        Integer animalId = toNumber(getRequestParam(AttributeConst.ANI_ID));
        //リクエストパラメータからチャット相手のユーザーIDを取得    **動物を購入希望の顧客または動物園ID
        Integer buyUserId = toNumber(getRequestParam(AttributeConst.CHAT_WITH));
        putRequestScope(AttributeConst.BUY_USER_ID, buyUserId);

        //セッションスコープからログイン中のUserIDを取得する
        UserView loginUser = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

        if (checkToken()) {

            //チャットを行うユーザーの組み合わせをチェックして取得する
            ChatView cv = chatService.findOneByOurID(loginUser.getId(), buyUserId);

            if (cv == null) {
                //組み合わせが存在しなかった場合
                forward(ForwardConst.FW_ERR_UNKNOWN);
                return;

            } else {

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
                } else {

                    //チャット画面を再表示する
                    putRequestScope(AttributeConst.TOKEN, getTokenId());
                    redirect(ForwardConst.ACT_ZOO, ForwardConst.CMD_TRADE_INDEX, animalId, buyUserId);
                }
            }
        }
    }


    /**
     * 顧客または購入希望の動物園とのチャットコメントの編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void tradeEdit() throws ServletException, IOException {

        //リクエストパラメータの動物idから動物データを取得して保存する。
        Integer animalId = toNumber(getRequestParam(AttributeConst.ANI_ID));
        putRequestScope(AttributeConst.ANIMAL, animalService.findOne(animalId));

        //セッション情報からログイン中のユーザーIDを取得
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);
        Integer myId = uv.getId();

        //リクエストスコープからチャット相手のユーザーIDを取得    **動物を購入希望の顧客または動物園ID
        Integer buyUserId = toNumber(getRequestParam(AttributeConst.CHAT_WITH));
        putRequestScope(AttributeConst.BUY_USER_ID, buyUserId);

        //チャットコメント情報を取得する
        List<CommentView> comments = commentService.getAllComment(animalId, buyUserId, myId);
        putRequestScope(AttributeConst.COMMENTS, comments);

        //リクエストスコープから編集するチャットコメントIDを取得
        Integer commentId = toNumber(getRequestParam(AttributeConst.COMMENT_EDIT));
        //コメントのIDを元にコメントインスタンスを取得する
        CommentView comment = commentService.findOne(commentId);
        putRequestScope(AttributeConst.COMMENT_EDIT, comment);

        if(comment == null || myId != comment.getChat().getMyUser().getId()) {

            //コメントが存在しない、または編集するコメントが自分の投稿でなければエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            //変更用のチャット画面を表示する
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            forward(ForwardConst.FW_ZOO_TRADE_EDIT);
        }
    }


    /**
     * 顧客または購入希望の動物園とのチャットコメントの更新を行う
     * @throws ServletException
     * @throws IOException
     */
    public void tradeUpdate() throws ServletException, IOException {

        //リクエストパラメータの動物idから動物データを取得して保存する。
        Integer animalId = toNumber(getRequestParam(AttributeConst.ANI_ID));
        putRequestScope(AttributeConst.ANIMAL, animalService.findOne(animalId));

        //リクエストスコープからチャット相手のユーザーIDを取得
        Integer buyUserId = toNumber(getRequestParam(AttributeConst.CHAT_WITH));
        putRequestScope(AttributeConst.BUY_USER_ID, buyUserId);

        if (checkToken()) {

            //編集するコメントのIDを元に、コメントインスタンスを取得する
            CommentView cv = commentService.findOne(toNumber(getRequestParam(AttributeConst.COMMENT_EDIT)));
            //編集後のコメント内容を取得する
            String newContent = getRequestParam(AttributeConst.COMMENT_CONTENT);

            cv.setContent(newContent);
            cv.setEditFlag(AttributeConst.EDIT_FLAG_TRUE.getIntegerValue());

            //コメントの更新処理を行う
            List<String> errors = commentService.update(cv);

            if (errors.size() > 0) {

                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.COMMENT, cv); //入力されたコメント情報
                putRequestScope(AttributeConst.ERR, errors);
                forward(ForwardConst.FW_ZOO_TRADE_EDIT);

            } else {
                //更新中にエラーが無かった場合

                //チャット画面を表示
                putRequestScope(AttributeConst.TOKEN, getTokenId());
                redirect(ForwardConst.ACT_ZOO, ForwardConst.CMD_TRADE_INDEX, animalId, buyUserId);
            }
        }
    }


    /**
     * 顧客または購入希望の動物園とのチャットコメントを削除する
     * @throws ServletException
     * @throws IOException
     */
    public void tradeDestroy() throws ServletException, IOException {

        //リクエストパラメータの動物idから動物データを取得して保存する。
        Integer animalId = toNumber(getRequestParam(AttributeConst.ANI_ID));
        putRequestScope(AttributeConst.ANIMAL, animalService.findOne(animalId));

        //リクエストスコープからチャット相手のユーザーIDを取得
        Integer buyUserId = toNumber(getRequestParam(AttributeConst.CHAT_WITH));
        putRequestScope(AttributeConst.BUY_USER_ID, buyUserId);

            //編集削除するコメントのIDを元に、コメントデータを削除する
            Comment c = commentService.findOneDTO(toNumber(getRequestParam(AttributeConst.COMMENT_EDIT)));
            if(c != null) {
                //DBからコメントデータを1行削除する
                commentService.destroy(c);

            //チャット画面を表示
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            redirect(ForwardConst.ACT_ZOO, ForwardConst.CMD_TRADE_INDEX, animalId, buyUserId);
        }
    }


    /**
     * 動物が販売済みとなり、取引が終了した案件の一覧を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void endTrade() throws ServletException, IOException {
        //セッションスコープからログイン中のUserとZooを取得する
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);
        ZooView zv = (ZooView) getSessionScope(AttributeConst.LOGIN_ZOO);
        //販売中、販売済の動物件数を取得しリクエストスコープに保存する
        putRequestScope(AttributeConst.ANI_SELLING_COUNT, animalService.countMySelling(zv));
        putRequestScope(AttributeConst.ANI_SOLD_COUNT, animalService.countMySold(zv));

        //チャット中の動物情報を取得し、リクエストスコープに保存する
        List<Object[]> trades = commentService.getZooEndIndex(uv.getId());
        putRequestScope(AttributeConst.COMMENT_TRADES, trades);

        //取引が終了した案件の件数をリクエストスコープに保存する
        int tradesCount = 0;
        if(trades.size() > 0) {
            tradesCount = trades.size();
        }
        putRequestScope(AttributeConst.TRADES_COUNT, tradesCount);

        List<CustomerView> custs = customerService.getAll();
        List<ZooView> zoos = zooService.getAll();
        putRequestScope(AttributeConst.CUSTOMERS, custs);
        putRequestScope(AttributeConst.ZOOS, zoos);

        if(zv == null || uv.getUserFlag() != AttributeConst.USER_ZOO.getIntegerValue()) {

            forward(ForwardConst.FW_ERR_UNKNOWN);
        } else {
            //動物園専用ページ画面を表示
            forward(ForwardConst.FW_ZOO_END_TRADE);
        }
    }


    /**
     * 動物園が購入しようとしている動物のチャット一覧を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void buyIndex() throws ServletException, IOException {

        //セッションスコープからログイン中のUserを取得する
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

        //チャット中の動物情報を取得し、リクエストスコープに保存する
        List<Object[]> trades = commentService.getIndex(uv.getId());
        putRequestScope(AttributeConst.COMMENT_TRADES, trades);

        if(uv != null && uv.getUserFlag() == AttributeConst.USER_ZOO.getIntegerValue()) {
            //顧客マイページ画面を表示
            forward(ForwardConst.FW_ZOO_BUY_INDEX);

        } else {
            forward(ForwardConst.FW_ERR_UNKNOWN);
        }
    }

}
