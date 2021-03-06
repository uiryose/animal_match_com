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
 * 顧客データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_CUSTOMER)
@NamedQueries({
        @NamedQuery(
                name = JpaConst.Q_CUST_GET_BY_USER_ID,
                query = JpaConst.Q_CUST_GET_BY_USER_ID_DEF),
        @NamedQuery(
                name = JpaConst.Q_CUST_GET_ALL,
                query = JpaConst.Q_CUST_GET_ALL_DEF)

})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.CUST_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Userクラス
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.ZOO_COL_USER, nullable = true)
    private User user;

    /**
     * 顧客名
     */
    @Column(name = JpaConst.CUST_COL_NAME, nullable = false)
    private String customerName;

    /**
     *登録日時
     */
    @Column(name = JpaConst.CUST_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.CUST_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

}
