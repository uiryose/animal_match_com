package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.CommentConverter;
import actions.views.CommentView;
import constants.JpaConst;
import models.Comment;
import models.validators.CommentValidator;

/**
 * チャットコメントテーブルの操作にかかわる操作を行うクラス
 */
public class CommentService extends ServiceBase {



    /**
     * 画面から入力されたコメントの登録内容を元にデータを1件作成し、コメントテーブルに登録する
     * @param cv
     * @return
     */
    public List<String> create(CommentView cv){

        List<String> errors = CommentValidator.validate(cv);
        if(errors.size()==0) {
            LocalDateTime ldt = LocalDateTime.now();
            cv.setCreatedAt(ldt);
            cv.setUpdatedAt(ldt);
            createInternal(cv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }


    /**
     * 指定した動物とユーザー同士のチャットをリストで取得する。
     * @param uv
     * @return
     */
    public List<CommentView> getAllComment(Integer animalId, Integer loginUserId, Integer companionUserId){

        List<Comment> comments = em.createNamedQuery(JpaConst.Q_COMMENT_GET_ALL_MINE, Comment.class)
                .setParameter(JpaConst.JPQL_PARM_ID, animalId)
                .setParameter(JpaConst.JPQL_PARM_MY_ID, loginUserId)
                .setParameter(JpaConst.JPQL_PARM_COMPANION_ID, companionUserId)
                .setParameter(JpaConst.JPQL_PARM_COMPANION_ID2, loginUserId)
                .setParameter(JpaConst.JPQL_PARM_MY_ID2, companionUserId)
                .getResultList();

        return CommentConverter.toViewList(comments);
    }







    /**
     * コメントデータを1件登録する
     * @param cv コメントデータ
     */
    private void createInternal(CommentView cv) {

        em.getTransaction().begin();
        em.persist(CommentConverter.toModel(cv));
        em.getTransaction().commit();
    }

}
