package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.ZooView;
import constants.MessageConst;


/**
 * 動物園インスタンスニ設定されている値のバリデーションを行うクラス
 *
 */
public class ZooValidator {


    /**
     * 動物園インスタンスの各項目についてバリデーションを行う
     * @param zv 画面に入力された動物園インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(ZooView zv){
        List<String> errors = new ArrayList<String>();

        //動物園名のチェック
        String nameError = validateName(zv.getZooName());
        if(!nameError.equals("")) {
            errors.add(nameError);
        }

        //所在地のチェック
        String regionError = validateRegion(zv.getRegion());
        if(!regionError.equals("")) {
            errors.add(regionError);
        }

        //電話番号のチェック
        String phoneError = validatePhone(zv.getPhone());
        if(!phoneError.equals("")) {
            errors.add(phoneError);
        }

        return errors;
    }


    /**
     * 名前に入力値があるかチェックし、入力値がなければエラーメッセージを返却
     * @param zooName
     * @return エラーメッセージ
     */
    private static String validateName(String zooName) {
        if(zooName == null || zooName.equals("")) {
            return  MessageConst.E_NOZOONAME.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 所在地に３～４文字の都道府県が入力されているかチェックし、入力値がなければエラーメッセージを返却
     * @param region 都道府県名
     * @return エラーメッセージ
     */
    private static String validateRegion(String region) {
        if(!region.matches("^(東京都|北海道|(京都|大阪)府|.{2,3}県)$")) {
//        if(region == null || region.equals("")) {
        return MessageConst.E_NOREGION.getMessage();
        }
        //正しい入力値がある場合は空文字を返却
        return "";

    }


    /**
     * 電話番号(数字のみチェック)が入力されているかチェックし、エラーを返す
     * @param phone
     * @return
     */
    private static String validatePhone(String phone) {
        if(!phone.matches("^[0-9]+$")) {
//        if(phone == null || phone.equals("")) {
        return MessageConst.E_NOPHONE.getMessage();
        }
        return "";

    }


}
