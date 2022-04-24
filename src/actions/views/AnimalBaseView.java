package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 * 基本動物情報について画面の入力値・出力値を扱うViewモデル
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalBaseView {

    /**
     * id
     */
    private Integer id;

    /**
     * 動物の名称
     */
    private String baseName;

    /**
     * 基本動物情報の画像
     */
    private String baseImage;

    /**
     * 基本動物情報のサイズ(0:大型、1:中型、2:小型)
     */
    private Integer baseSize;

    /**
     * 動物の飼育難易度(１～５段階評価)
     */
    private Integer baseDifficulty;

    /**
     * 基本動物の個人飼育フラグ(0:可能、1:不可)
     */
    private Integer baseBreedFlag;

    /**
     * 基本動物情報の特徴コメント
     */
    private String baseComment;

    /**
     *登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;


}
