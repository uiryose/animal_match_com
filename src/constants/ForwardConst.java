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
    ACT_BASE("AnimalBase"),
    ACT_ANI("Animal"),
    ACT_AUTH("Auth"),
    ACT_LIKE("Like"),
    ACT_CHAT("Chat"),

    //command
    CMD("command"),
    CMD_NONE(""),
    CMD_INDEX("index"),
    CMD_SHOW("show"),
    CMD_SHOWSELL("showSell"),
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
    CMD_SELLING("showSelling"),
    CMD_SOLD("showSold"),
    CMD_TRADE_INDEX("tradeIndex"),
    CMD_TRADE_CREATE("tradeCreate"),
    CMD_TRADE_EDIT("tradeEdit"),
    CMD_TRADE_UPDATE("tradeUpdate"),
    CMD_TRADE_DESTROY("tradeDestroy"),

    //jsp
    FW_ERR_UNKNOWN("error/unknown"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),
    FW_ZOO_INDEX("zoos/index"),
    FW_ZOO_SHOW("zoos/show"),
    FW_ZOO_NEW("zoos/new"),
    FW_ZOO_EDIT("zoos/edit"),
    FW_ZOO_SELLING("zoos/selling"),
    FW_ZOO_SOLD("zoos/sold"),
    FW_ZOO_TRADE_INDEX("zoos/tradeIndex"),
    FW_ZOO_TRADE_EDIT("zoos/tradeEdit"),
    FW_CUST_INDEX("customers/index"),
    FW_CUST_SHOW("customers/show"),
    FW_CUST_NEW("customers/new"),
    FW_CUST_EDIT("customers/edit"),
    FW_CUST_LIKE("customers/like"),
    FW_ANI_INDEX("animals/index"),
    FW_ANI_SHOW("animals/show"),
    FW_ANI_NEW("animals/new"),
    FW_ANI_EDIT("animals/edit"),
    FW_BASE_INDEX("animalbases/index"),
    FW_BASE_SHOW("animalbases/show"),
    FW_BASE_SHOWSELL("animalbases/showSell"),
    FW_BASE_NEW("animalbases/new"),
    FW_BASE_EDIT("animalbases/edit"),
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