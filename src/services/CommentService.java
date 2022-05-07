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
     * マイページに表示するために、チャット取引のある動物別一覧を取得する
     * @param loginUserId ログインしているユーザーのID
     * @return チャット取引中の動物一覧
     */
    public List<Object[]> getIndex(Integer loginUserId) {

        List<Object[]> commentIndex = em.createNamedQuery(JpaConst.Q_COMMENT_GET_INDEX, Object[].class)
                .setParameter(JpaConst.JPQL_PARM_MY_ID, loginUserId)
                .getResultList();

        return commentIndex;
    }


    /**
     * idを条件に取得したコメントデータをCommentViewインスタンスで返却する
     * @param id
     * @return CommentViewインスタンス
     */
    public CommentView findOne(int id) {

        Comment c = findOneInternal(id);
        return CommentConverter.toView(c);

    }

    /**
     * idを条件に取得したコメントデータをDTOモデルで返却する
     * @param id
     * @return
     */
    public Comment findOneDTO(int id) {

        return findOneInternal(id);
    }


    /**
     * 動物園マイページに表示する取引中(販売)の動物を取得する
     * @param loginUserId
     * @return
     */
    public List<Object[]> getZooIndex(Integer loginUserId){

        List<Object[]> commentZooIndex = em.createNamedQuery(JpaConst.Q_COMMENT_GET_ZOO_INDEX, Object[].class)
                .setParameter(JpaConst.JPQL_PARM_MY_ID, loginUserId)
                .getResultList();

        return commentZooIndex;

    }






    /**
     * 画面から入力されたコメント内容を元に、コメントデータを更新する
     * @param cv
     * @return
     */
    public List<String> update(CommentView cv){

        //バリデーションを行う
        List<String> errors = CommentValidator.validate(cv);

        if(errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            cv.setUpdatedAt(ldt);
            updateInternal(cv);
        }
        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;

    }


    /**
     * コメントデータを削除する
     * @param c コメントデータ
     */
    public void destroy(Comment c) {

        em.getTransaction().begin();
        em.remove(c);  //データ削除
        em.getTransaction().commit();
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


    /**
     * コメントデータを更新する
     * @param cv 画面から入力されたコメントデータ
     */
    private void updateInternal(CommentView cv) {
        em.getTransaction().begin();
        Comment a = findOneInternal(cv.getId());
        CommentConverter.copyViewToModel(a, cv);
        em.getTransaction().commit();
    }


    /**
     * idを条件にデータを1件取得し、Commentのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private Comment findOneInternal(int id) {
        Comment c = em.find(Comment.class, id);
        return c;
    }


}
