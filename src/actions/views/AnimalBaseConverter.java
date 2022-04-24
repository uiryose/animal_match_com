package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.AnimalBase;

/**
 * 基本動物情報データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class AnimalBaseConverter {


    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param bv AnimalBaseViewのインスタンス
     * @return AnimalBaseのインスタンス
     */
    public static AnimalBase toModel(AnimalBaseView bv) {

        return new AnimalBase(
                bv.getId(),
                bv.getBaseName(),
                bv.getBaseImage(),
                bv.getBaseSize(),
                bv.getBaseDifficulty(),
                bv.getBaseBreedFlag(),
                bv.getBaseComment(),
                bv.getCreatedAt(),
                bv.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param b AnimalBaseのインスタンス
     * @return AnimalBaseViewのインスタンス
     */

    public static AnimalBaseView toView(AnimalBase b) {

        if(b == null) {
            return null;
        }

        return new AnimalBaseView(
                b.getId(),
                b.getBaseName(),
                b.getBaseImage(),
                b.getBaseSize(),
                b.getBaseDifficulty(),
                b.getBaseBreedFlag(),
                b.getBaseComment(),
                b.getCreatedAt(),
                b.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<AnimalBaseView> toViewList(List<AnimalBase> list){

        List<AnimalBaseView> bvl = new ArrayList<>();

        for (AnimalBase b : list) {
            bvl.add(toView(b));
        }

        return bvl;

    }

    /**
    * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
    * @param b DTOモデル(コピー先)
    * @param bv Viewモデル(コピー元)
    */
    public static void copyViewToModel(AnimalBase b, AnimalBaseView bv) {
        b.setId(bv.getId());
        b.setBaseName(bv.getBaseName());
        b.setBaseImage(bv.getBaseImage());
        b.setBaseSize(bv.getBaseSize());
        b.setBaseDifficulty(bv.getBaseDifficulty());
        b.setBaseBreedFlag(bv.getBaseBreedFlag());
        b.setBaseComment(bv.getBaseComment());
        b.setCreatedAt(bv.getCreatedAt());
        b.setUpdatedAt(bv.getUpdatedAt());
    }

}
