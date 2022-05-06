package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.CommentView;
import constants.MessageConst;


/**
 * コメントインスタンス設定されている値のバリデーションを行うクラス
 *
 */
public class CommentValidator {


    /**
     * コメントインスタンスの各項目についてバリデーションを行う
     * @param cv 画面に入力されたコメント内容
     * @return エラーのリスト
     */
    public static List<String> validate(CommentView cv){
        List<String> errors = new ArrayList<String>();

        //氏名のチェック
        String contentError = validateContent(cv.getContent());
        if(!contentError.equals("")) {
            errors.add(contentError);
        }

        return errors;
    }


    /**
     * 内容に入力値があるかチェックし、入力値がなければエラーメッセージを返却
     * @param content
     * @return エラーメッセージ
     */
    private static String validateContent(String content) {
        if(content == null || content.equals("")) {
            return  MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }



}
