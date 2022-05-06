package models;

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
 * チャットの送信元、送信先データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_CHAT)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_CHAT_GET_BY_OUR_ID,
            query = JpaConst.Q_CHAT_GET_BY_OUR_ID_DEF)



})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Chat {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.CHAT_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 送信元のUser id
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.CHAT_COL_MY_USER, nullable = true)
    private User myUser;

    /**
     * 送信先のUser id
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.CHAT_COL_COMPANION_USER, nullable = true)
    private User companionUser;


}
