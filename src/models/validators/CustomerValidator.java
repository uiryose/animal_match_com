package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.CustomerView;
import constants.MessageConst;


/**
 * 顧客インスタンスニ設定されている値のバリデーションを行うクラス
 *
 */
public class CustomerValidator {


    /**
     * 顧客インスタンスの各項目についてバリデーションを行う
     * @param zv 画面に入力された顧客インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(CustomerView cv){
        List<String> errors = new ArrayList<String>();

        //氏名のチェック
        String nameError = validateName(cv.getCustomerName());
        if(!nameError.equals("")) {
            errors.add(nameError);
        }

        return errors;
    }


    /**
     * 名前に入力値があるかチェックし、入力値がなければエラーメッセージを返却
     * @param customerName
     * @return エラーメッセージ
     */
    private static String validateName(String customerName) {
        if(customerName == null || customerName.equals("")) {
            return  MessageConst.E_NONAME.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }



}
