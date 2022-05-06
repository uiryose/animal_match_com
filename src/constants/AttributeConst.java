package constants;

/**
 * 画面の項目値等を定義するEnumクラス
 *
 */
public enum AttributeConst {

    //フラッシュメッセージ
    FLUSH("flush"),

    //一覧画面共通
    MAX_ROW("maxRow"),
    PAGE("page"),

    //入力フォーム共通
    TOKEN("_token"),
    ERR("errors"),

    //ログイン中のユーザー
    LOGIN_USER("login_user"),
    LOGIN_ZOO("login_zoo"),
    LOGIN_CUSTOMER("login_customer"),

    //ログイン画面
    LOGIN_ERR("loginError"),

    //ユーザーUser管理
    USER("user"),
    USERS("users"),
    USER_ID("id"),
    USER_CODE("code"),
    USER_PASSWORD("password"),
    USER_FLAG("user_flag"),

    //動物園Zoo管理
    ZOO("zoo"),
    ZOOS("zoos"),
    ZOO_ID("id"),
    ZOO_NAME("zoo_name"),
    ZOO_REGION("region"),
    ZOO_PHONE("phone"),

    //顧客Customer管理
    CUSTOMER("customer"),
    CUSTOMERS("customers"),
    CUST_ID("id"),
    CUST_NAME("cust_name"),

    //ユーザーフラグ
    USER_CUST(0),
    USER_ZOO(1),

    //管理者フラグ
    ROLE_GENERAL(0),
    ROLE_ADMIN(1),

    //削除フラグ
    DEL_FLAG_FALSE(0),
    DEL_FLAG_TRUE(1),  //削除済み

    //編集フラグ
    EDIT_FLAG_FALSE(0),
    EDIT_FLAG_TRUE(1),  //編集済み

    //動物基本情報管理AnimalBase
    ANIMALBASE("animalbase"),
    ANIMALBASES("animalbases"),
    BASE_COUNT("bases_count"),
    BASE_ID("id"),
    BASE_NAME("base_name"),
    BASE_IMAGE("base_image"),
    BASE_SIZE("base_size"),
    BASE_DIFFICULTY("base_difficulty"),
    BASE_BREED_FLAG("base_breed_flag"),
    BASE_COMMENT("base_comment"),

    //動物販売情報管理
    ANIMAL("animal"),
    ANIMALS("animals"),
    ANI_COUNT("animals_count"),
    ANI_SELLING_COUNT("selling_count"),
    ANI_SOLD_COUNT("sold_count"),
    ANI_ID("id"),
    ANI_NICKNAME("animal_nickname"),
    ANI_IMAGE("animal_image"),
    ANI_AGE("animal_age"),
    ANI_SEX("animal_sex"),
    PRICE_FOR_CUST("price_for_cust"),
    PRICE_FOR_ZOO("price_for_zoo"),
    ZOO_COMMENT("zoo_comment"),
    SOLD_FLAG("sold_flag"),

    //性別フラグ
    SEX_MALE(0), //オス
    SEX_FEMALE(1), //メス
    SEX_UNKNOWN(2), //不明

    //動物サイズ
    SIZE_L(0), //大型
    SIZE_M(1), //中型
    SIZE_S(2), //小型

    //個人飼育フラグ
    BREED_FLAG_TURE(0),//飼育可能
    BREED_FLAG_FALSE(1),

    //販売済みフラグ
    SOLD_FLAG_FALSE(0),
    SOLD_FLAG_TRUE(1), //販売済み


    //お気に入り
    LIKE("like"),
    LIKES("likes"),
    LIKE_ID("id"),

    //チャット
    CHAT("chat"),
    CHATS("chats"),
    CHAT_ID("id"),
    CHAT_ANI_ID("ani_id"),
    CHAT_ZOO_ID("zoo_id"),
    CHAT_CUST_ID("cust_id"),
    CHAT_CONTENT("content"),
    CHAT_WITH("with"),

    //コメント
    COMMENT("comment"),
    COMMENTS("comments"),
    COMMENT_ID("comment_id");


    private final String text;
    private final Integer i;

    private AttributeConst(final String text) {
        this.text = text;
        this.i = null;
    }

    private AttributeConst(final Integer i) {
        this.text = null;
        this.i = i;
    }

    public String getValue() {
        return this.text;
    }

    public Integer getIntegerValue() {
        return this.i;
    }

}