package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 動物園について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZooView {

    /**
     * id
     */
    private Integer id;

    /**
     * Userクラス
     */
    private UserView user;

    /**
     * 動物園名
     */
    private String zooName;

    /**
     * 動物園の所在地(都道府県名)
     */
    private String region;

    /**
     * 動物園の電話番号
     */
    private String phone;

    /**
     *登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;

}
