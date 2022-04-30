package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 * 動物データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_ANIMAL)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_ANI_GET_MY_SELLING,
            query = JpaConst.Q_ANI_GET_MY_SELLING_DEF),
    @NamedQuery(
            name = JpaConst.Q_ANI_COUNT_MY_SELLING,
            query = JpaConst.Q_ANI_COUNT_MY_SELLING_DEF)




})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Animal {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.ANI_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * AnimalBaseクラス
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.ANI_COL_BASE, nullable = false)
    private AnimalBase animalBase;

    /**
     * 動物を掲載したZooクラス
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.ANI_COL_ZOO, nullable = false)
    private Zoo zoo;

    /**
     * 動物の愛称
     */
    @Column(name = JpaConst.ANI_COL_NICKNAME, nullable = false)
    private String nickname;

    /**
     * 動物の画像
     */
    @Column(name = JpaConst.ANI_COL_IMAGE, nullable = true)
    private String animalImage;

    /**
     * 動物の年齢
     */
    @Column(name = JpaConst.ANI_COL_AGE, nullable = false)
    private Integer animalAge;

    /**
     * 動物の性別(0:オス、1:メス、2:不明)
     */
    @Column(name = JpaConst.ANI_COL_SEX, nullable = false)
    private Integer animalSex;

    /**
     * 動物の価格(個人向け)
     */
    @Column(name = JpaConst.ANI_COL_PRICE_FOR_CUST, nullable = false)
    private Integer priceForCust;

    /**
     * 動物の価格(動物園向け)
     */
    @Column(name = JpaConst.ANI_COL_PRICE_FOR_ZOO, nullable = false)
    private Integer priceForZoo;

    /**
     * 動物園からのコメント
     */
    @Lob
    @Column(name = JpaConst.ANI_COL_COMMENT, nullable = false)
    private String animalComment;

    /**
     * 販売済みかどうか(0:現役、1:販売済み)
     */
    @Column(name = JpaConst.ANI_COL_SOLD_FLAG, nullable = false)
    private Integer soldFlag;

    /**
     *登録日時
     */
    @Column(name = JpaConst.ANI_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.ANI_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;


}
