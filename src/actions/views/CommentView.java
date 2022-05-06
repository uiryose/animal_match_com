package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * チャット画面の入力値・出力値を扱うViewモデル
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentView {

    /**
     * id
     */
    private Integer id;

    /**
     * 対象の販売動物
     */
    private AnimalView animal;

    /**
     * チャット送信元、送信先を管理
     */
    private ChatView chat;

    /**
     * チャットコメントの内容
     */
    private String content;

    /**
     * コメントが編集を管理(0:未編集、1:編集済み)
     */
    private Integer editFlag;

    /**
     *登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;


}
