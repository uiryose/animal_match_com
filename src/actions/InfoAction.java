package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import constants.ForwardConst;

/**
 * 静的画面のフォワード処理を管理するクラス
 *
 */
public class InfoAction extends ActionBase {

    @Override
    public void process() throws ServletException, IOException {

        //メソッドの実行
        invoke();

    }

    /**
     * 「サイトの趣旨」の画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void introduce() throws ServletException, IOException {
System.out.println("テスト：サイトの趣旨ページです");
        //「サイトの趣旨」画面を表示
        forward(ForwardConst.FW_INFO_INTRODUCE);
    }

    /**
     * 「ご利用前に」の画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void beforeUse() throws ServletException, IOException {

        //「ご利用の前に」画面を表示
        forward(ForwardConst.FW_INFO_BEFOREUSE);
    }

    /**
     * 「制作コメント」の画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void myMesseage() throws ServletException, IOException {

        //「制作コメント」画面を表示
        forward(ForwardConst.FW_INFO_MYMESSEAGE);
    }

    /**
     * 「お問い合わせ」の画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void feedback() throws ServletException, IOException {

        //「お問い合わせ」画面を表示
        forward(ForwardConst.FW_INFO_FEEDBACK);
    }

    /**
     * 「エサMatch.com」の画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void esaMatch() throws ServletException, IOException {

        //「エサMatch.com」画面を表示
        forward(ForwardConst.FW_INFO_ESAMATCH);
    }










}
