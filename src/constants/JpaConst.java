package constants;

/**
 * DB関連の項目値を定義するインターフェース
 * ※インターフェイスに定義した変数は public static final 修飾子がついているとみなされる
 */
public interface JpaConst {

    //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "animal_match_com";

    //データ取得件数の最大値
    int ROW_PER_PAGE = 12; //1ページに表示するレコードの数

    //ユーザーテーブル
    String TABLE_USER ="users"; //テーブル名
    //ユーザーテーブルカラム
    String USER_COL_ID = "id"; //id
    String USER_COL_CODE = "code"; //code
    String USER_COL_USER_FLAG = "user_flag"; //code
    String USER_COL_PASS = "password"; //パスワード

    //顧客テーブル
    String TABLE_CUSTOMER = "customers";
    //顧客テーブルカラム
    String CUST_COL_ID = "id"; //id
    String CUST_COL_USER = "user_id"; //ユーザーテーブルのid
    String CUST_COL_NAME = "customer_name"; //名前
    String CUST_COL_DELETE_FLAG = "delete_flag"; //削除フラグ
    String CUST_COL_CREATED_AT = "created_at"; //登録日時
    String CUST_COL_UPDATED_AT = "updated_at"; //更新日時

    //動物園テーブル
    String TABLE_ZOO = "zoos"; //テーブル名
    //動物園テーブルカラム
    String ZOO_COL_ID = "id"; //id
    String ZOO_COL_USER = "user_id"; //ユーザーテーブルのid
    String ZOO_COL_NAME = "zoo_name"; //動物園名
    String ZOO_COL_REGION = "zoo_region"; //所在地
    String ZOO_COL_PHONE = "zoo_phone"; //電話番号
    String ZOO_COL_DELETE_FLAG = "delete_flag"; //削除フラグ
    String ZOO_COL_CREATED_AT = "created_at"; //登録日時
    String ZOO_COL_UPDATED_AT = "updated_at"; //更新日時


    //ユーザーフラグ
    int USER_CUST = 0; //顧客ユーザー
    int USER_ZOO = 1; //動物園ユーザー

    int ROLE_ADMIN = 1; //管理者権限ON(管理者)
    int ROLE_GENERAL = 0; //管理者権限OFF(一般)

    int ANI_SOLD_FALSE = 0; //販売済フラグOFF(現役)
    int ANI_SOLD_TURE = 1; //販売済フラグON(販売済み)

    int USER_DEL_FALSE = 0; //削除フラグOFF(現役)
    int USER_DEL_TRUE = 1; //削除フラグON(削除済み)
//    int ZOO_DEL_FALSE = 0; //削除フラグOFF(現役)
//    int ZOO_DEL_TRUE = 1; //削除フラグON(削除済み)
//    int CUST_DEL_FALSE = 0; //削除フラグOFF(現役)
//    int CUST_DEL_TRUE = 1; //削除フラグON(削除済み)
    int CHAT_EDIT_FALSE = 0; //編集フラグOFF(現役)
    int CHAT_EDIT_TRUE = 1; //編集フラグON(編集済み)
    int CHAT_DEL_FALSE = 0; //削除フラグOFF(現役)
    int CHAT_DEL_TRUE = 1; //削除フラグON(削除済み)

    int SEX_MALE = 0; //オス
    int SEX_FEMALE = 1; //メス
    int SEX_UNKNOWN = 2; //不明


    //動物基本情報テーブル
    String TABLE_ANIMAL_BASE = "animalbases"; //テーブル名
    //動物基本情報テーブルカラム
    String BASE_COL_ID = "id"; //id
    String BASE_COL_NAME = "base_name"; //名前
    String BASE_COL_IMAGE = "base_image"; //動物の画像
    String BASE_COL_SIZE = "base_size"; //動物のサイズ
    String BASE_COL_DIFFICULTY = "base_difficulty"; //飼育難易度
    String BASE_COL_BREED_FLAG = "base_breed_flag"; //個人飼育フラグ
    String BASE_COL_COMMENT = "base_comment"; //日報の内容
    String BASE_COL_CREATED_AT = "created_at"; //登録日時
    String BASE_COL_UPDATED_AT = "updated_at"; //更新日時

    //動物販売情報テーブル
    String TABLE_ANIMAL = "animals"; //テーブル名
    //動物販売情報テーブルカラム
    String ANI_COL_ID = "id"; //id
    String ANI_COL_BASE = "base_id"; //動物基本情報のid
    String ANI_COL_ZOO = "zoo_id"; //動物園のid
    String ANI_COL_NICKNAME = "nickname"; //愛称
    String ANI_COL_IMAGE = "animal_image"; //動物の画像
    String ANI_COL_AGE = "animal_age"; //動物の年齢
    String ANI_COL_SEX = "animal_sex"; //動物の性別
    String ANI_COL_PRICE_FOR_CUST = "price_for_cust"; //個人向け価格
    String ANI_COL_PRICE_FOR_ZOO = "price_for_zoo"; //動物園向け価格
    String ANI_COL_COMMENT = "animal_comment"; //動物園からのコメント
    String ANI_COL_SOLD_FLAG = "sold_flag"; //販売済みフラグ
    String ANI_COL_CREATED_AT = "created_at"; //登録日時
    String ANI_COL_UPDATED_AT = "updated_at"; //更新日時


    //チャットのコメントテーブル
    String TABLE_COMMENT = "comments"; //テーブル名
    //チャットのコメントテーブルカラム
    String COM_COL_ID = "id"; //id
    String COM_COL_ANI = "ani_id"; //日報を作成した従業員のid
    String COM_COL_CHAT = "chat_id"; //チャットを送信ユーザーを管理するChatクラスのid
    String COM_COL_CONTENT = "content"; //チャットの内容
    String COM_COL_EDIT_FLAG = "edit_flag"; //編集フラグ
    String COM_COL_DELETE_FLAG = "delete_flag"; //削除フラグ
    String COM_COL_CREATED_AT = "created_at"; //登録日時
    String COM_COL_UPDATED_AT = "updated_at"; //更新日時


    //チャットテーブル
    String TABLE_CHAT = "chats";
    //チャットテーブルカラム
    String CHAT_COL_ID = "id"; //id
    String CHAT_COL_MY_USER = "my_id"; //チャットを送信したユーザーid
    String CHAT_COL_COMPANION_USER = "companion_id"; //チャットを送信したユーザーid

/**
    //お気に入りテーブル
    String TABLE_LIKE = "likes"; //テーブル名
    //お気に入りテーブルカラム
    String LIKE_COL_ID = "id"; //id
    String LIKE_COL_ANI = "ani_id"; //お気に入り先の動物id
    String LIKE_COL_CUST = "cust_id"; //お気に入り元の顧客id
    String LIKE_COL_ZOO_ID = "zoo_id"; //お気に入り元の動物園id
    String LIKE_COL_CREATED_AT = "created_at"; //登録日時
*/

    //Entity名
    String ENTITY_BASE = "animalbase"; //動物基本情報
    String ENTITY_ANI = "animal"; //動物販売
    String ENTITY_USER= "user"; //ユーザー
    String ENTITY_CUST= "customer"; //顧客
    String ENTITY_ZOO = "zoo"; //動物園
    String ENTITY_LIKE = "like"; //お気に入り
    String ENTITY_CHAT = "chat"; //チャット
    String ENTITY_COMMENT = "chat"; //コメント


    //JPQL内パラメータ
    String JPQL_PARM_ID = "id"; //id
    String JPQL_PARM_ZOO = "zoo"; //Zoo
    String JPQL_PARM_CODE = "code"; //code
    String JPQL_PARM_NAME = "name";
    String JPQL_PARM_PASSWORD = "password";
    String JPQL_PARM_ANIMALBASE = "animalBase";
    String JPQL_PARM_ANIMAL = "animal";
    String JPQL_PARM_LIKE = "like";
    String JPQL_PARM_IMAGE ="image";
    String JPQL_PARM_BREED_FLAG = "base_breed_flag"; //個人飼育フラグ
    String JPQL_PARM_SOLD_FLG = "sold_flag"; //販売済みフラグ
    String JPQL_PARM_DELET_FLG = "delete_flag"; //削除フラグ
    String JPQL_PARM_MY_ID = "my_id"; //自分のユーザーID
    String JPQL_PARM_MY_ID2 = "my_id"; //自分のユーザーID
    String JPQL_PARM_COMPANION_ID = "companion_id"; //相手のユーザーID
    String JPQL_PARM_COMPANION_ID2 = "companion_id"; //相手のユーザーID



    //NamedQueryの nameとquery
//Userクラス
    //ユーザーコードとハッシュ化済パスワードを条件に未削除のユーザーを取得する
    String Q_USER_GET_BY_CODE_AND_PASS = ENTITY_USER + ".getByCodeAndPass";
    String Q_USER_GET_BY_CODE_AND_PASS_DEF = "SELECT u FROM User AS u WHERE u.deleteFlag = 0 AND u.code = :" + JPQL_PARM_CODE + " AND u.password = :" + JPQL_PARM_PASSWORD;

    //指定したコードを保持するユーザーの件数を取得する
    String Q_USER_COUNT_RESISTERED_BY_CODE = ENTITY_USER + ".countRegisteredByCode";
    String Q_USER_COUNT_RESISTERED_BY_CODE_DEF = "SELECT COUNT(u) FROM User AS u WHERE u.code = :" + JPQL_PARM_CODE;


//AnimalaBaseクラス
    //全ての基本動物情報を名前順に取得する
    String Q_BASE_GET_ALL = ENTITY_BASE + ".getAll";
    String Q_BASE_GET_ALL_DEF = "SELECT b FROM AnimalBase AS b ORDER BY b.baseName";
    //全ての基本動物情報を件数を取得する
    String Q_BASE_COUNT = ENTITY_BASE + ".countAll";
    String Q_BASE_COUNT_DEF = "SELECT COUNT(b) FROM AnimalBase AS b";
    //名前検索した動物に部分一致する情報を取得する
    String Q_BASE_GET_SEARCH_BY_NAME = ENTITY_BASE + ".getSearchAll";
    String Q_BASE_GET_SEARCH_BY_NAME_DEF = "SELECT b FROM AnimalBase AS b WHERE b.baseName LIKE : " + JPQL_PARM_NAME;
    //個人飼育フラグに対応する基本動物情報を名前順に取得する
    String Q_BASE_GET_SEARCH_BY_BREED_FLAG = ENTITY_BASE + ".getByBreedFlag";
    String Q_BASE_GET_SEARCH_BY_BREED_FLAG_DEF = "SELECT b FROM AnimalBase AS b WHERE b.baseBreedFlag = :" + JPQL_PARM_BREED_FLAG + " ORDER BY b.baseName";
    //個人飼育フラグに対応する基本動物情報を件数を取得する
    String Q_BASE_COUNT_BY_BREED_FLAG = ENTITY_BASE + ".countByBreedFlag";
    String Q_BASE_COUNT_BY_BREED_FLAG_DEF = "SELECT COUNT(b) FROM AnimalBase AS b WHERE b.baseBreedFlag = :" + JPQL_PARM_BREED_FLAG;




//Animalクラス
    //全ての販売動物をidの降順に取得する
    String Q_ANI_GET_ALL = ENTITY_ANI + ".getAll";
    String Q_ANI_GET_ALL_DEF = "SELECT a FROM Animal AS a ORDER BY a.id DESC";
    //全ての販売動物の件数を取得する
    String Q_ANI_COUNT = ENTITY_ANI + ".count";
    String Q_ANI_COUNT_DEF = "SELECT COUNT(a) FROM Animal AS a";
   ///指定した基本動物情報の販売動物を全件idの降順で取得する
    String Q_ANI_GET_BY_BASE_ID = ENTITY_ANI + ".getByBaseId";
    String Q_ANI_GET_BY_BASE_ID_DEF = "SELECT a FROM Animal AS a WHERE a.animalBase = : " + JPQL_PARM_ANIMALBASE + " ORDER BY a.id DESC";
   ///指定した基本動物情報の販売動物の件数を取得する
    String Q_ANI_COUNT_BY_BASE_ID = ENTITY_ANI + ".countByBaseId";
    String Q_ANI_COUNT_BY_BASE_ID_DEF = "SELECT COUNT(a) FROM Animal AS a WHERE a.animalBase = : " + JPQL_PARM_ANIMALBASE;
   ///指定した動物園が販売中の動物の一覧を取得する
    String Q_ANI_GET_MY_SELLING = ENTITY_ANI + ".getMySelling";
    String Q_ANI_GET_MY_SELLING_DEF = "SELECT a FROM Animal AS a WHERE a.zoo = : " + JPQL_PARM_ZOO + " AND a.soldFlag = : " + JPQL_PARM_SOLD_FLG + " ORDER BY a.animalBase.baseName";
   ///指定した動物園が販売中の動物の件数を取得する
    String Q_ANI_COUNT_MY_SELLING = ENTITY_ANI + ".countMySelling";
    String Q_ANI_COUNT_MY_SELLING_DEF = "SELECT COUNT(a) FROM Animal AS a WHERE a.zoo = : " + JPQL_PARM_ZOO + " AND a.soldFlag = : " + JPQL_PARM_SOLD_FLG ;
   ///指定した動物園が販売済みの動物の一覧を取得する
    String Q_ANI_GET_MY_SOLD = ENTITY_ANI + ".getMySold";
    String Q_ANI_GET_MY_SOLD_DEF = "SELECT a FROM Animal AS a WHERE a.zoo = : " + JPQL_PARM_ZOO + " AND a.soldFlag = : " + JPQL_PARM_SOLD_FLG + " ORDER BY a.animalBase.baseName";
   ///指定した動物園が販売済の動物の件数を取得する
    String Q_ANI_COUNT_MY_SOLD = ENTITY_ANI + ".countMySold";
    String Q_ANI_COUNT_MY_SOLD_DEF = "SELECT COUNT(a) FROM Animal AS a WHERE a.zoo = : " + JPQL_PARM_ZOO + " AND a.soldFlag = : " + JPQL_PARM_SOLD_FLG ;
   ///基本動物ごとの販売件数を取得する**
    String Q_ANI_COUNT_GROUP_BY_BASE_ID = ENTITY_ANI + ".countGroupByBaseId";
    String Q_ANI_COUNT_GROUP_BY_BASE_ID_DEF = "SELECT a.animalBase.id, count(a) FROM Animal AS a GROUP BY a.animalBase.id ORDER BY a.animalBase.id";



//Customerクラス
    //指定したUserのidで顧客情報を取得する
    String Q_CUST_GET_BY_USER_ID = ENTITY_CUST + ".getByUserId";
    String Q_CUST_GET_BY_USER_ID_DEF = "SELECT c FROM Customer AS c WHERE c.user.id = :" + JPQL_PARM_ID;
    //全ての顧客をidの降順に取得する
    String Q_CUST_GET_ALL = ENTITY_CUST + ".getAll";
    String Q_CUST_GET_ALL_DEF = "SELECT c FROM Customer AS c ORDER BY c.id";


//Zooクラス
    //指定したUserのidで動物園情報を取得する
    String Q_ZOO_GET_BY_USER_ID = ENTITY_ZOO + ".getByUserId";
    String Q_ZOO_GET_BY_USER_ID_DEF = "SELECT z FROM Zoo AS z WHERE z.user.id = :" + JPQL_PARM_ID;
    //全ての動物園をidの降順に取得する
    String Q_ZOO_GET_ALL = ENTITY_ZOO + ".getAll";
    String Q_ZOO_GET_ALL_DEF = "SELECT z FROM Zoo AS z ORDER BY z.id";


//Chatクラス
    //チャットの送信元、送信先のIDの組み合わせを取得する
    String Q_CHAT_GET_BY_OUR_ID = ENTITY_CHAT + ".getByOurId";
    String Q_CHAT_GET_BY_OUR_ID_DEF = "SELECT c FROM Chat AS c WHERE c.myUser.id = :" + JPQL_PARM_MY_ID + " AND c.companionUser.id = :" +JPQL_PARM_COMPANION_ID ;



//Commentクラス
    //特定の動物におけるチャット履歴を取得する
    String Q_COMMENT_GET_ALL_MINE = ENTITY_COMMENT + ".getALLMine";
    String Q_COMMENT_GET_ALL_MINE_DEF = "SELECT c FROM Comment AS c WHERE c.animal.id = :" + JPQL_PARM_ID
            + " AND ((c.chat.myUser.id = :" + JPQL_PARM_MY_ID + " AND c.chat.companionUser.id = : " + JPQL_PARM_COMPANION_ID + " ) "
            + " OR (c.chat.myUser.id = :" + JPQL_PARM_COMPANION_ID2 + " AND c.chat.companionUser.id = : " + JPQL_PARM_MY_ID2 + " )) "
            + "ORDER BY c.createdAt";

    //顧客マイページに表示する取引中の動物を取得する
    String Q_COMMENT_GET_INDEX = ENTITY_COMMENT + ".getIndex";
    String Q_COMMENT_GET_INDEX_DEF = "SELECT c FROM Comment AS c WHERE c.chat.myUser.id = : " + JPQL_PARM_MY_ID
            + " OR  c.chat.companionUser.id = : " + JPQL_PARM_MY_ID
            + " GROUP BY c.animal.id ORDER BY c.animal.animalBase";

    //動物園マイページに表示する取引中(販売)の動物を取得する
    String Q_COMMENT_GET_ZOO_INDEX = ENTITY_COMMENT + "getZooIndex";
    String Q_COMMENT_GET_ZOO_INDEX_DEF = "SELECT DISTINCT c.animal, c.chat FROM Comment AS c WHERE c.animal.zoo.user.id = : " + JPQL_PARM_MY_ID + " AND c.chat.companionUser.id = :" + JPQL_PARM_MY_ID +" AND c.animal.soldFlag = 0" ;
    //動物園マイページに表示する取引が終了した案件を取得する
    String Q_COMMENT_GET_ZOO_END_INDEX = ENTITY_COMMENT + "getZooEndIndex";
    String Q_COMMENT_GET_ZOO_END_INDEX_DEF = "SELECT DISTINCT c.animal, c.chat FROM Comment AS c WHERE c.animal.zoo.user.id = : " + JPQL_PARM_MY_ID + " AND c.chat.companionUser.id = :" + JPQL_PARM_MY_ID +" AND c.animal.soldFlag = :" + JPQL_PARM_SOLD_FLG ;



}
