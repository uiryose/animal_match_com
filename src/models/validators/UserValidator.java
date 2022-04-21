package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.UserView;
import constants.MessageConst;
import services.UserService;

/**
 * Userインスタンスに設定されている値のバリデーションを行うクラス
 *
 */
public class UserValidator {

    public static List<String> validate(
            UserService service, UserView uv, Boolean codeDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        //ユーザーコードのチェック
        String codeError = validateCode(service, uv.getCode(), codeDuplicateCheckFlag);
        if (!codeError.equals("")) {
            errors.add(codeError);
        }

        //パスワードのチェック
        String passError = validatePassword(uv.getPassword(), passwordCheckFlag);
        if (!passError.equals("")) {
            errors.add(passError);
        }

        return errors;
    }

    /**
     * ユーザーコードの入力チェックを行い、エラーメッセージを返却
     * @param service UserServiceのインスタンス
     * @param code
     * @param codeDuplicateCheckFlag コードの重複チェックを実施するかどうか(実施する:true 実施しない:false)
     * @return エラーメッセージ
     */
    private static String validateCode(UserService service, String code, Boolean codeDuplicateCheckFlag) {

        //入力値がなければエラーメッセージを返却
        if (code == null || code.equals("")) {
            return MessageConst.E_NOUSER_CODE.getMessage();
        }

        if (codeDuplicateCheckFlag) {
            //コードの重複チェックを実施

            long usersCount = isDuplicateUser(service, code);

            //同一コードが既に登録されている場合はエラーメッセージを返却
            if (usersCount > 0) {
                return MessageConst.E_CODE_EXIST.getMessage();
            }
        }

        //エラーがない場合は空文字を返却
        return "";
    }

    /**
     * @param service UserServiceのインスタンス
     * @param code
     * @return ユーザーテーブルに登録されている同一社員番号のデータの件数
     */
    private static long isDuplicateUser(UserService service, String code) {

        long usersCount = service.countByCode(code);
        return usersCount;
    }

    /**
     * パスワードの入力チェックを行い、エラーメッセージを返却
     * @param password パスワード
     * @param passwordCheckFlag パスワードの入力チェックを実施するかどうか(実施する:true 実施しない:false)
     * @return エラーメッセージ
     */
    private static String validatePassword(String password, Boolean passwordCheckFlag) {

        //入力チェックを実施 かつ 入力値がなければエラーメッセージを返却
        if (passwordCheckFlag && (password == null || password.equals(""))) {
            return MessageConst.E_NOPASSWORD.getMessage();
        }

        //エラーがない場合は空文字を返却
        return "";
    }

}
