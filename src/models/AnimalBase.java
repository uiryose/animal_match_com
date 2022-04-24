package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 * 動物基本情報データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_ANIMAL_BASE)
/*@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_BASE_GET_ALL,
            query = JpaConst.Q_BASE_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_BASE_COUNT,
            query = JpaConst.Q_BASE_COUNT_DEF)

})
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AnimalBase {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.BASE_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 動物の名称
     */
    @Column(name = JpaConst.BASE_COL_NAME, nullable = false)
    private String baseName;

    /**
     * 基本動物情報の画像
     */
    @Column(name = JpaConst.BASE_COL_IMAGE, nullable = true)
    private String baseImage;

    /**
     * 基本動物情報のサイズ(0:大型、1:中型、2:小型)
     */
    @Column(name = JpaConst.BASE_COL_SIZE, nullable = false)
    private Integer baseSize;

    /**
     * 動物の飼育難易度(１～５段階評価)
     */
    @Column(name = JpaConst.BASE_COL_DIFFICULTY, nullable = false)
    private Integer baseDifficulty;

    /**
     * 基本動物の個人飼育フラグ(0:可能、1:不可)
     */
    @Column(name = JpaConst.BASE_COL_BREED_FLAG, nullable = false)
    private Integer baseBreedFlag;

    /**
     * 基本動物情報の特徴コメント
     */
    @Lob
    @Column(name = JpaConst.BASE_COL_COMMENT, nullable = false)
    private String baseComment;

    /**
     *登録日時
     */
    @Column(name = JpaConst.BASE_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.BASE_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;


}
