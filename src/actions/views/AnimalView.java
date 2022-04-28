package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 販売する動物について画面の入力値・出力値を扱うViewモデル
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalView {

    /**
     * id
     */
    private Integer id;

    /**
     * AnimalBaseクラス
     */
    private AnimalBaseView animalBase;

    /**
     * 動物を掲載したZooクラス
     */
    private ZooView zoo;

    /**
     * 動物の愛称
     */
    private String nickname;

    /**
     * 動物の画像
     */
    private String animalImage;

    /**
     * 動物の年齢
     */
    private Integer animalAge;

    /**
     * 動物の性別(0:オス、1:メス、2:不明)
     */
    private Integer animalSex;

    /**
     * 動物の価格(個人向け)
     */
    private Integer priceForCust;

    /**
     * 動物の価格(動物園向け)
     */
    private Integer priceForZoo;

    /**
     * 動物園からのコメント
     */
    private String animalComment;

    /**
     * 販売済みかどうか(0:現役、1:販売済み)
     */
    private Integer soldFlag;

    /**
     *登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;


}