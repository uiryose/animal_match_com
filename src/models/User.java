package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 * ユーザーデータのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_USER)


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.USER_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * ユーザーコード
     */
    @Column(name = JpaConst.USER_COL_CODE, nullable = false, unique = true)
    private String code;

    /**
     * ユーザーの種別を管理（顧客：0、動物園：1）
     */
    @Column(name = JpaConst.USER_COL_USER_FLAG, nullable = false)
    private Integer userFlag;

    /**
     * パスワード
     */
    @Column(name = JpaConst.USER_COL_PASS, length = 64, nullable = false)
    private String password;

}