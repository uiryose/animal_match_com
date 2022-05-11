package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.AnimalBaseView;
import actions.views.AnimalView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import services.AnimalBaseService;
import services.AnimalService;

public class AnimalBaseAction extends ActionBase {


    private AnimalBaseService animalBaseService;
    private AnimalService animalService;

    @Override
    public void process() throws ServletException, IOException {

        animalBaseService = new AnimalBaseService();
        animalService = new AnimalService();

        //メソッドの実行
        invoke();

        animalBaseService.close();
        animalService.close();
    }


    /**
     * トップページに基本動物情報の一覧を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException{

        //指定されたページ数の一覧画面に表示する基本動物情報データを取得
        int page = getPage();
        List<AnimalBaseView> animalbases = animalBaseService.getAllPerPage(page);

        //全基本動物情報データの件数を取得
        long animalbasesCount = animalBaseService.countAll();

        putRequestScope(AttributeConst.ANIMALBASES, animalbases); //取得した基本動物情報データ
        putRequestScope(AttributeConst.BASE_COUNT, animalbasesCount); //全ての基本動物情報データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //各基本動物毎の掲載実績の件数を取得
        List<Object[]> sellCountList = animalService.getCountByBaseId();

        putRequestScope(AttributeConst.ANI_COUNT, sellCountList);

        //検索メソッドからのアクセスを認識するセッション情報を削除する
        removeSessionScope(AttributeConst.SEARCHING);

        //一覧画面を表示
        forward(ForwardConst.FW_TOP_INDEX);
    }


    /**
     * 基本動物情報の詳細を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

        //基本動物データのidを元にデータを1件取得し、リクエストスコープの保存する
        AnimalBaseView bv = animalBaseService.findOne(toNumber(getRequestParam(AttributeConst.BASE_ID)));
        putRequestScope(AttributeConst.ANIMALBASE, bv);

        //基本動物データのidと同じ販売動物の一覧を取得する
        List<AnimalView> avl = animalService.getByBaseId(bv);
        putRequestScope(AttributeConst.ANIMALS, avl);

        //基本動物データのidと同じ販売動物の件数を取得する
        //

        //動物詳細ページ画面を表示
        forward(ForwardConst.FW_BASE_SHOW);

    }

    /**
     * 動物の販売個体情報画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void showSell() throws ServletException, IOException {

        //リクエストスコープからログイン情報、動物情報を取得する
        AnimalView av = animalService.findOne(toNumber(getRequestParam(AttributeConst.ANI_ID)));
        putRequestScope(AttributeConst.ANIMAL, av);

        putRequestScope(AttributeConst.TOKEN, getTokenId());

        //販売動物の詳細(チャットを促す画面)を表示
        forward(ForwardConst.FW_BASE_SHOWSELL);
    }


    /**
     * 検索項目に一致するデータを表示する
     * @throws ServletException
     * @throws IOException
     */
    public void searchName() throws ServletException, IOException{

        //パラメータから検索する動物の名前を取得する
        String name = getRequestParam(AttributeConst.BASE_NAME);

        if(name == null || name.equals("") ) {
            //検索内容が空白の場合は、一覧表示にリダイレクトする
            redirect(ForwardConst.ACT_BASE, ForwardConst.CMD_INDEX);
            return;
        }

        //指定された検索項目に一致するデータを取得し、リクエストスコープに保存する
        List<AnimalBaseView> animalbases = animalBaseService.getSearchByName(name);
        putRequestScope(AttributeConst.ANIMALBASES, animalbases);

        //この検索メソッドから一覧画面が表示された場合は、通常の一覧表示とJSPの表示を分ける
        String callMethod = AttributeConst.SEARCH.getValue();
        putRequestScope(AttributeConst.CALL_METHOD, callMethod);

        //各基本動物毎の掲載実績の件数を取得
        List<Object[]> sellCountList = animalService.getCountByBaseId();
        putRequestScope(AttributeConst.ANI_COUNT, sellCountList);

        //検索メソッドからのアクセスをセッションに記録する
        putSessionScope(AttributeConst.SEARCHING, "_searching");

        //一覧画面を表示
        forward(ForwardConst.FW_TOP_INDEX);
    }


    /**
     * 検索機能として個人飼育の可否で絞り込みをする
     * @throws ServletException
     * @throws IOException
     */
    public void searchBreedFlag() throws ServletException, IOException {

        int page = getPage();
        Integer breedFlag = toNumber(getRequestParam(AttributeConst.BASE_BREED_FLAG));

        //指定された検索項目に一致するデータを取得し、リクエストスコープに保存する
        List<AnimalBaseView> animalbases = animalBaseService.getSearchByBreedFlag(page, breedFlag);
        putRequestScope(AttributeConst.ANIMALBASES, animalbases);

        //個人飼育フラグに対する基本動物情報データの件数を取得
        long animalbasesCount = animalBaseService.countByBreedFlag(breedFlag);
        putRequestScope(AttributeConst.BASE_COUNT, animalbasesCount); //飼育フラグに対応する基本動物情報データの件数
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

        //各基本動物毎の掲載実績の件数を取得
        List<Object[]> sellCountList = animalService.getCountByBaseId();
        putRequestScope(AttributeConst.ANI_COUNT, sellCountList);

        //検索メソッドからのアクセスをセッションに記録する
        putSessionScope(AttributeConst.SEARCHING, "_searching");

        //一覧画面を表示
        forward(ForwardConst.FW_TOP_INDEX);
    }




    //基本動物情報の新規登録、編集、削除等は個人ユーザー・動物園に権限はなく、サービス運営者のみ可能です。
    //そのため、当アプリケーションでは上記機能の実装まではせずアプリケーション管理人がデータベースにSQL文で直接操作するものとします。




}
