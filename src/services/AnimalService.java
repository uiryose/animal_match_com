package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.AnimalConverter;
import actions.views.AnimalView;
import constants.JpaConst;
import models.Animal;
import models.validators.AnimalValidator;

/**
 * 販売動物テーブルの操作に関わる処理を行うクラス
 */
public class AnimalService extends ServiceBase {



    /**
     * idを条件に取得したデータをAnimalのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public AnimalView findOne(int id) {
        Animal a = findOneInternal(id);
        return AnimalConverter.toView(a);
    }


    /**
     * AnimalBaseのidを元に動物情報を取得し、Viewモデルで返却する
     * @param id AnimalBaseのid
     * @return AnimalViewのインスタンス
     */
 //要修正
    public AnimalView findAllByBaseId(int id) {
        Animal z = (Animal) em.createNamedQuery(JpaConst.Q_ZOO_GET_BY_USER_ID, Animal.class)
                .setParameter(JpaConst.JPQL_PARM_ID, id)
                .getSingleResult();

        return AnimalConverter.toView(z);
    }


    //AnimalBaseのidを元に動物情報を取得し、掲載された動物の件数を取得し、返却する
    public long countAllByBaseId(int id) {
        long count=1;



        return count;
    }






    /**
     * 画面から入力された動物の登録内容を元にデータを1件作成し、動物テーブルに登録する
     * @param av
     * @return
     */
    public List<String> create(AnimalView av){
        List<String> errors = AnimalValidator.validate(av);
        if(errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            av.setCreatedAt(ldt);
            av.setUpdatedAt(ldt);
            createInternal(av);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }


    /**
     * 画面から入力された動物の登録内容を元に、動物データを更新する
     * @param av
     * @return
     */
    public List<String> update(AnimalView av){

        //バリデーションを行う
        List<String> errors = AnimalValidator.validate(av);

        if(errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            av.setUpdatedAt(ldt);
            updateInternal(av);
        }
        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;

    }



    /**
     * idを条件にデータを1件取得し、Animalのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private Animal findOneInternal(int id) {
        Animal a = em.find(Animal.class, id);
        return a;
    }


    /**
     * 動物データを1件登録する
     * @param av 動物データ
     */
    private void createInternal(AnimalView av) {
        em.getTransaction().begin();
        em.persist(AnimalConverter.toModel(av));
        em.getTransaction().commit();
    }


    /**
     * 動物データを更新する
     * @param av 画面から入力された動物データ
     */
    private void updateInternal(AnimalView av) {
        em.getTransaction().begin();
        Animal a = findOneInternal(av.getId());
        AnimalConverter.copyViewToModel(a, av);
        em.getTransaction().commit();
    }

}
