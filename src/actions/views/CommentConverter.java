package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Comment;


/**
 * 動物データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class CommentConverter {


    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param cv CommentViewのインスタンス
     * @return Commentのインスタンス
     */
    public static Comment toModel(CommentView cv) {

        return new Comment(
                cv.getId(),
                AnimalConverter.toModel(cv.getAnimal()),
                ChatConverter.toModel(cv.getChat()),
                cv.getContent(),
                cv.getEditFlag(),
                cv.getCreatedAt(),
                cv.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param c Commentのインスタンス
     * @return CommentViewのインスタンス
     */

    public static CommentView toView(Comment c) {

        if(c == null) {
            return null;
        }

        return new CommentView(
                c.getId(),
                AnimalConverter.toView(c.getAnimal()),
                ChatConverter.toView(c.getChat()),
                c.getContent(),
                c.getEditFlag(),
                c.getCreatedAt(),
                c.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<CommentView> toViewList(List<Comment> list){

        List<CommentView> cvl = new ArrayList<>();

        for (Comment c : list) {
            cvl.add(toView(c));
        }

        return cvl;

    }

    /**
    * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
    * @param c DTOモデル(コピー先)
    * @param cv Viewモデル(コピー元)
    */
    public static void copyViewToModel(Comment c, CommentView cv) {
        c.setId(cv.getId());
        c.setAnimal(AnimalConverter.toModel(cv.getAnimal()));
        c.setChat(ChatConverter.toModel(cv.getChat()));
        c.setContent(cv.getContent());
        c.setEditFlag(cv.getEditFlag());
        c.setCreatedAt(cv.getCreatedAt());
        c.setUpdatedAt(cv.getUpdatedAt());
    }

}
