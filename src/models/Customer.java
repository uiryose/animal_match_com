package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
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
@Table(name = JpaConst.TABLE_CUSTOMER)
@NamedQueries({




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
    @Column(name = JpaConst.CUST_COL_USER, nullable = false)
    private User user;

    /**
     * 顧客名
     */
    @Column(name = JpaConst.CUST_COL_NAME, nullable = false)
    private String customerName;

//    /**
//     * 削除された顧客かどうか（現役：0、削除済み：1）
//     */
//    @Column(name = JpaConst.CUST_COL_DELETE_FLAG, nullable = false)
//    private Integer deleteFlag;

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
