package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import constants.ForwardConst;
import services.AnimalBaseService;

public class AnimalBaseAction extends ActionBase {


    private AnimalBaseService animalBaseService;

    @Override
    public void process() throws ServletException, IOException {

        animalBaseService = new AnimalBaseService();

        //メソッドの実行
        invoke();
        animalBaseService.close();
    }

    /*

    public void index() throws ServletException, IOException{

        //指定されたページ数の一覧画面に表示する基本動物情報データを取得
        int page = getPage();
        List<AnimalBaseView> animalbases = animalBaseService.getAllPerPage(page);

        //全基本動物情報データの件数を取得
        long reportsCount = animalBaseService.countAll();

        putRequestScope(AttributeConst.ANIMALBASE, animalbases); //取得した基本動物情報データ
        putRequestScope(AttributeConst.BASE_COUNT, reportsCount); //全ての基本動物情報データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_TOP_INDEX);
    }

    */


    public void index() throws ServletException, IOException{

        forward(ForwardConst.FW_TOP_INDEX);
    }




    //基本動物情報の新規登録、編集、削除等は個人ユーザー・動物園に権限はなく、サービス運営者のみ可能です。
    //そのため、当アプリケーションでは実装まではせずアプリケーション管理人がデータベースにSQL文で直接操作するものとします。




}
