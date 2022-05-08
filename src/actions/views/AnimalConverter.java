package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Animal;


/**
 * 動物データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class AnimalConverter {


    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param av AnimalViewのインスタンス
     * @return Animalのインスタンス
     */
    public static Animal toModel(AnimalView av) {

        return new Animal(
                av.getId(),
                AnimalBaseConverter.toModel(av.getAnimalBase()),
                ZooConverter.toModel(av.getZoo()),
                av.getNickname(),
                av.getAnimalImage(),
                av.getAnimalAge(),
                av.getAnimalSex(),
                av.getPriceForCust(),
                av.getPriceForZoo(),
                av.getAnimalComment(),
                av.getSoldFlag(),
                av.getCreatedAt(),
                av.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param a Animalのインスタンス
     * @return AnimalViewのインスタンス
     */

    public static AnimalView toView(Animal a) {

        if(a == null) {
            return null;
        }

        return new AnimalView(
                a.getId(),
                AnimalBaseConverter.toView(a.getAnimalBase()),
                ZooConverter.toView(a.getZoo()),
                a.getNickname(),
                a.getAnimalImage(),
                a.getAnimalAge(),
                a.getAnimalSex(),
                a.getPriceForCust(),
                a.getPriceForZoo(),
                a.getAnimalComment(),
                a.getSoldFlag(),
                a.getCreatedAt(),
                a.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<AnimalView> toViewList(List<Animal> list){

        List<AnimalView> avl = new ArrayList<>();

        for (Animal a : list) {
            avl.add(toView(a));
        }

        return avl;

    }

    /**
    * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
    * @param a DTOモデル(コピー先)
    * @param av Viewモデル(コピー元)
    */
    public static void copyViewToModel(Animal a, AnimalView av) {
        a.setId(av.getId());
        a.setAnimalBase(AnimalBaseConverter.toModel(av.getAnimalBase()));
        a.setZoo(ZooConverter.toModel(av.getZoo()));
        a.setNickname(av.getNickname());

        if( av.getAnimalImage() != null) {
            a.setAnimalImage(av.getAnimalImage());
        }
        a.setAnimalAge(av.getAnimalAge());
        a.setAnimalSex(av.getAnimalSex());
        a.setPriceForCust(av.getPriceForCust());
        a.setPriceForZoo(av.getPriceForZoo());
        a.setAnimalComment(av.getAnimalComment());
        a.setSoldFlag(av.getSoldFlag());
        a.setCreatedAt(av.getCreatedAt());
        a.setUpdatedAt(av.getUpdatedAt());
    }

}
