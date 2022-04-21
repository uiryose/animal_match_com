package actions.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ユーザー情報について画面の入力値・出力値を扱うViewモデル
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserView {


    /**
     * id
     */
    private Integer id;

    /**
     * code
     */
    private String code;

    /**
     * ユーザーの種別を管理（顧客：0、動物園：1）
     */
    private Integer userFlag;

    /**
     * パスワード
     */
    private String password;

}
