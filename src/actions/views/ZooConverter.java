package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Zoo;

public class ZooConverter {


    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param zv ZooViewのインスタンス
     * @return Zooのインスタンス
     */
    public static Zoo toModel(ZooView zv) {
        return new Zoo(
                zv.getId(),
                UserConverter.toModel(zv.getUser()),
                zv.getZooName(),
                zv.getRegion(),
                zv.getPhone(),
                zv.getCreatedAt(),
                zv.getUpdatedAt());
    }



    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param z Zooのインスタンス
     * @return ZooViewのインスタンス
     */
    public static ZooView toView(Zoo z) {

        if(z == null) {
            return null;
        }

        return new ZooView(
                z.getId(),
                UserConverter.toView(z.getUser()),
                z.getZooName(),
                z.getRegion(),
                z.getPhone(),
                z.getCreatedAt(),
                z.getUpdatedAt());
    }


    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<ZooView> toViewList(List<Zoo> list){
        List<ZooView> zvl = new ArrayList<>();

        for(Zoo z : list) {
            zvl.add(toView(z));
        }
        return zvl;

    }


    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param z DTOモデル(コピー先)
     * @param zv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Zoo z, ZooView zv) {
        z.setId(zv.getId());
        z.setUser(UserConverter.toModel(zv.getUser()));
        z.setZooName(zv.getZooName());
        z.setRegion(zv.getRegion());
        z.setPhone(zv.getPhone());
        z.setCreatedAt(zv.getCreatedAt());
        z.setUpdatedAt(zv.getUpdatedAt());
    }

}
