package actions.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * チャットの送信元、送信先ユーザーidを管理するViewモデル
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatView {

    /**
     * id
     */
    private Integer id;

    /**
     * Userクラス（自分）
     */
    private UserView myUser;


    /**
     * Userクラス（相手）
     */
    private UserView companionUser;


}
