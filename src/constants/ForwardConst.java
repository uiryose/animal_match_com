package constants;

/**
 * リクエストパラメーターの変数名、変数値、jspファイルの名前等画面遷移に関わる値を定義するEnumクラス
 *
 */
public enum ForwardConst {

    //action
    ACT("action"),
    ACT_TOP("Top"),
    ACT_USER("User"),
    ACT_CUST("Customer"),
    ACT_ZOO("Zoo"),
    ACT_BASE("Base"),
    ACT_ANI("Animal"),
    ACT_AUTH("Auth"),
    ACT_LIKE("Like"),
    ACT_CHAT("Chat"),

    //command
    CMD("command"),
    CMD_NONE(""),
    CMD_INDEX("index"),
    CMD_SHOW("show"),
    CMD_SHOW_LOGIN("showLogin"),
    CMD_LOGIN("login"),
    CMD_LOGOUT("logout"),
    CMD_NEW("entryNew"),
    CMD_CREATE("create"),
    CMD_EDIT("edit"),
    CMD_UPDATE("update"),
    CMD_DESTROY("destroy"),
    CMD_ALL("all"),
    CMD_LIKE("like"),
    CMD_REMOVE("remove"),

    //jsp
    FW_ERR_UNKNOWN("error/unknown"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),
    FW_ZOO_INDEX("zoos/index"),
    FW_ZOO_SHOW("zoos/show"),
    FW_ZOO_NEW("zoos/new"),
    FW_ZOO_EDIT("zoos/edit"),
    FW_CUST_INDEX("customers/index"),
    FW_CUST_SHOW("customers/show"),
    FW_CUST_NEW("customers/new"),
    FW_CUST_EDIT("customers/edit"),
    FW_CUST_LIKE("customers/like"),
    FW_ANI_INDEX("animals/index"),
    FW_ANI_SHOW("animals/show"),
    FW_ANI_NEW("animals/new"),
    FW_ANI_EDIT("animals/edit"),
    FW_BASE_INDEX("bases/index"),
    FW_BASE_SHOW("bases/show"),
    FW_BASE_NEW("bases/new"),
    FW_BASE_EDIT("bases/edit"),
    FW_LIKE_INDEX("likes/index"),
    FW_CHAT_INDEX("chats/index"),
    FW_CHAT_EDIT("chats/edit");

    /**
     * 文字列
     */
    private final String text;

    /**
     * コンストラクタ
     */
    private ForwardConst(final String text) {
        this.text = text;
    }

    /**
     * 値(文字列)取得
     */
    public String getValue() {
        return this.text;
    }

}