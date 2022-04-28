package models.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import actions.views.AnimalView;
import constants.MessageConst;


/**
 * 販売動物インスタンスに設定されている値のバリデーションを行うクラス
 *
 */
public class Animal {


    /**
     * 動物園インスタンスの各項目についてバリデーションを行う
     * @param av 画面に入力された動物園インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(AnimalView av){
        List<String> errors = new ArrayList<String>();

        //動物園名のチェック
        String nicknameError = validateNickname(av.getNickname());
        if(!nicknameError.equals("")) {
            errors.add(nicknameError);
        }

        //価格(個人向け)のチェック
        String priceForCustError = validatePriceForCust(av.getPriceForCust().toString());
        if(!priceForCustError.equals("")) {
            errors.add(priceForCustError);
        }

        //価格(動物園向け)のチェック
        String priceForZooError = validatePriceForZoo(av.getPriceForZoo().toString());
        if(!priceForZooError.equals("")) {
            errors.add(priceForZooError);
        }

        //動物園からのメッセージのチェック
        String commentError = validateComment(av.getAnimalComment());
        if(!commentError.equals("")) {
            errors.add(commentError);
        }

        return errors;
    }


    /**
     * 愛称に入力値があるかチェックし、入力値がなければエラーメッセージを返却
     * @param nickname 愛称
     * @return エラーメッセージ
     */
    private static String validateNickname(String nickname) {
        if(nickname == null || nickname.equals("")) {
            return  MessageConst.E_NONICKNAME.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }


    /**
     * 価格(個人向け)に入力値があるかチェックし、入力値がなければエラーメッセージを返却
     * @param priceForCust 個人向け価格
     * @return エラーメッセージ
     */
    private static String validatePriceForCust(String priceForCust) {

        Pattern pattern = Pattern.compile("^[0-9]+$|-[0-9]+$");

        if(!pattern.matcher(priceForCust).matches()) {
            return  MessageConst.E_NOPRICEFORCUST.getMessage();
        }

        //負の整数・正の整数で入力値がある場合は空文字を返却
        return "";
    }


    /**
     * 価格(動物園向け)に入力値があるかチェックし、入力値がなければエラーメッセージを返却
     * @param priceForZoo 動物園向け価格
     * @return エラーメッセージ
     */
    private static String validatePriceForZoo(String priceForZoo) {

        Pattern pattern = Pattern.compile("^[0-9]+$");

        if(!pattern.matcher(priceForZoo).matches()) {
            return  MessageConst.E_NOPRICEFORZOO.getMessage();
        }

        //正の整数で入力値がある場合は空文字を返却
        return "";
    }


    /**
     * メッセージが入力されているかチェックし、入力値がなければエラーメッセージを返却
     * @param comment 動物園からのメッセージ
     * @return エラーメッセージ
     */
    private static String validateComment(String comment) {
        if(comment == null || comment.equals("")) {
        return MessageConst.E_NOCOMMENT.getMessage();
        }
        //正しい入力値がある場合は空文字を返却
        return "";

    }


}
