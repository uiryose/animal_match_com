package actions.views;

import models.Chat;

public class ChatConverter {


    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param cv ChatViewのインスタンス
     * @return Chatのインスタンス
     */
    public static Chat toModel(ChatView cv) {

        return new Chat(
                cv.getId(),
                UserConverter.toModel(cv.getMyUser()),
                UserConverter.toModel(cv.getCompanionUser()));
    }


    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param c Chatのインスタンス
     * @return ChatViewのインスタンス
     */
    public static ChatView toView(Chat c) {

        if(c == null) {
            return null;
        }

        return new ChatView(
                c.getId(),
                UserConverter.toView(c.getMyUser()),
                UserConverter.toView(c.getCompanionUser()));
        }
//
//    /**
//     * DTOモデルのリストからViewモデルのリストを作成する
//     * @param list DTOモデルのリスト
//     * @return Viewモデルのリスト
//     */
//    public static List<ChatView> toViewList(List<Chat> list) {
//        List<ChatView> cvl = new ArrayList<>();
//
//        for (Chat u : list) {
//            cvl.add(toView(u));
//        }
//        return cvl;
//    }


    /**
     * ViewモデルのリストからDTOモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static void copyViewToModel(Chat c, ChatView cv) {
        c.setId(cv.getId());
        c.setMyUser(UserConverter.toModel(cv.getMyUser()));
        c.setCompanionUser(UserConverter.toModel(cv.getCompanionUser()));
    }
}
