package services;

import javax.persistence.NoResultException;

import actions.views.ChatConverter;
import actions.views.ChatView;
import constants.JpaConst;
import models.Chat;

/**
 * チャットテーブルの操作にかかわる処理を行うクラス
 *
 */
public class ChatService extends ServiceBase {


    public ChatView findOne(int id) {
        Chat c = findOneInternal(id);
        return ChatConverter.toView(c);
    }


    /**
     * チャットをやり取りするユーザーのパターンを検索する
     * @param myId
     * @param companionId
     * @return
     */
    public ChatView findOneByOurID(int myId, int companionId) {
        Chat c = null;

        try {
            c = (Chat) em.createNamedQuery(JpaConst.Q_CHAT_GET_BY_OUR_ID, Chat.class)
                    .setParameter(JpaConst.JPQL_PARM_MY_ID, myId)
                    .setParameter(JpaConst.JPQL_PARM_COMPANION_ID, companionId)
                    .getSingleResult();

        } catch (NoResultException e) {
        }

        return ChatConverter.toView(c);
    }


    /**
     * チャットデータを2件登録する
     * @param cv1 送信元自分、送信先相手動物園
     * @param cv2 送信元相手動物園、送信先自分
     * @return
     */
    public ChatView create(ChatView cv1, ChatView cv2) {

        return createInternal(cv1, cv2);
    }


    /**
     * チャットデータを2件登録する
     * @param cv1 送信元自分、送信先相手動物園
     * @param cv2 送信元相手動物園、送信先自分
     */
    private ChatView createInternal(ChatView cv1, ChatView cv2) {

        em.getTransaction().begin();
        Chat c1 = ChatConverter.toModel(cv1);
        em.persist(c1);
        em.persist(ChatConverter.toModel(cv2));
        em.getTransaction().commit();

        return ChatConverter.toView(c1);
    }


    private Chat findOneInternal(int id) {
        Chat c = em.find(Chat.class, id);
        return c;
    }


}
