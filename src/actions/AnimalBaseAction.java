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

        System.out.println("テスト：" + animalService.getCountByBaseId());
        putRequestScope(AttributeConst.ANI_COUNT, sellCountList);

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



    //基本動物情報の新規登録、編集、削除等は個人ユーザー・動物園に権限はなく、サービス運営者のみ可能です。
    //そのため、当アプリケーションでは実装まではせずアプリケーション管理人がデータベースにSQL文で直接操作するものとします。




}
