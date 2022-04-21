package constants;

/**
 * 各出力メッセージを定義するEnumクラス
 *
 */
public enum MessageConst {

    //認証
    I_LOGINED("ログインしました"),
    E_LOGINED("ログインに失敗しました。"),
    I_LOGOUT("ログアウトしました。"),

    //DB更新
    I_REGISTERED("登録が完了しました。"),
    I_UPDATED("更新が完了しました。"),
    I_DELETED("削除が完了しました。"),

    //バリデーション
    E_NONAME("氏名を入力してください。"),
    E_NOPASSWORD("パスワードを入力してください。"),
    E_NOREGION("所在地を入力してください。"),
    E_NOPHONE("電話番号を入力してください。"),
    E_NOAGE("年齢を入力してください。"),
    E_NOSEX("性別を入力してください。"),
    E_NOPRICE("価格を入力してください。"),
    E_NOCOMMENT("コメントを入力してください。"),
    E_NOTITLE("タイトルを入力してください。"),
    E_NOCONTENT("内容を入力してください。"),
    E_CODE_EXIST("入力されたコードは既に使われています。"),
    E_NOUSER_CODE("コードを入力してください。");


    /**
     * 文字列
     */
    private final String text;

    /**
     * コンストラクタ
     */
    private MessageConst(final String text) {
        this.text = text;
    }

    /**
     * 値(文字列)取得
     */
    public String getMessage() {
        return this.text;
    }
}