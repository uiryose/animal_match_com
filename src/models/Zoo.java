package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * 動物園データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_ZOO)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_ZOO_GET_BY_USER_ID,
            query = JpaConst.Q_ZOO_GET_BY_USER_ID_DEF),
    @NamedQuery(
    name = JpaConst.Q_ZOO_GET_ALL,
    query = JpaConst.Q_ZOO_GET_ALL_DEF)


})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Zoo {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.ZOO_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Userクラス
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.ZOO_COL_USER, nullable = true)
    private User user;

    /**
     * 動物園名
     */
    @Column(name = JpaConst.ZOO_COL_NAME, nullable = false)
    private String zooName;

    /**
     * 動物園の所在地(都道府県名)
     */
    @Column(name = JpaConst.ZOO_COL_REGION, nullable = false)
    private String region;

    /**
     * 動物園の電話番号
     */
    @Column(name = JpaConst.ZOO_COL_PHONE, nullable = false)
    private String phone;

//    /**
//     * 削除された動物園かどうか（現役：0、削除済み：1）
//     */
//    @Column(name = JpaConst.ZOO_COL_DELETE_FLAG, nullable = false)
//    private Integer deleteFlag;

    /**
     *登録日時
     */
    @Column(name = JpaConst.ZOO_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.ZOO_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;


}
