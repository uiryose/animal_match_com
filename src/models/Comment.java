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
 * コメントデータのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_COMMENT)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_COMMENT_GET_ALL_MINE,
            query = JpaConst.Q_COMMENT_GET_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_COMMENT_GET_INDEX,
            query = JpaConst.Q_COMMENT_GET_INDEX_DEF),
    @NamedQuery(
            name = JpaConst.Q_COMMENT_GET_ZOO_INDEX,
            query = JpaConst.Q_COMMENT_GET_ZOO_INDEX_DEF)




})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.COM_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Animalクラス
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.COM_COL_ANI, nullable = false)
    private Animal animal;

    /**
     * チャット送信元、送信先を管理するChatクラス
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.COM_COL_CHAT, nullable = false)
    private Chat chat;

    /**
     * チャットコメントの内容
     */
    @Lob
    @Column(name = JpaConst.COM_COL_CONTENT, nullable = false)
    private String content;

    /**
     * 編集済みかどうか(0:未編集、1:編集済み)
     */
    @Column(name = JpaConst.COM_COL_EDIT_FLAG, nullable = false)
    private Integer editFlag;

    /**
     *登録日時
     */
    @Column(name = JpaConst.COM_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.COM_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;


}
