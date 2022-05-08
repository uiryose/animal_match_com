package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.AnimalBaseConverter;
import actions.views.AnimalBaseView;
import actions.views.AnimalConverter;
import actions.views.AnimalView;
import actions.views.ZooConverter;
import actions.views.ZooView;
import constants.AttributeConst;
import constants.JpaConst;
import models.Animal;
import models.validators.AnimalValidator;

/**
 * 販売動物テーブルの操作に関わる処理を行うクラス
 */
public class AnimalService extends ServiceBase {



    /**
     * idを条件に取得したデータをAnimalViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public AnimalView findOne(int id) {
        Animal a = findOneInternal(id);
        return AnimalConverter.toView(a);
    }

    /**
     * idを条件に取得したデータをAnimalのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public Animal findOneModel(int id) {
        Animal a = findOneInternal(id);
        return a;
    }

//    /**
//     * AnimalBaseのidを元に動物情報を取得し、Viewモデルで返却する
//     * @param id AnimalBaseのid
//     * @return AnimalViewのインスタンス
//     */
// //要修正
//    public AnimalView findAllByBaseId(int id) {
//        Animal a = (Animal) em.createNamedQuery(JpaConst.Q_ZOO_GET_BY_USER_ID, Animal.class)
//                .setParameter(JpaConst.JPQL_PARM_ID, id)
//                .getSingleResult();
//
//        return AnimalConverter.toView(a);
//    }


    /**
     * 指定した動物園の販売中の動物一覧を取得し、AnimalViewリストで返却する
     * @param zv 動物園情報
     * @return 指定した動物園が販売する動物情報（販売済みは除く）
     */
    public List<AnimalView> getMySelling(ZooView zv) {
        List<Animal> animals = em.createNamedQuery(JpaConst.Q_ANI_GET_MY_SELLING, Animal.class)
                .setParameter(JpaConst.JPQL_PARM_ZOO, ZooConverter.toModel(zv))
                .setParameter(JpaConst.JPQL_PARM_SOLD_FLG, AttributeConst.SOLD_FLAG_FALSE.getIntegerValue())
                .getResultList();

        return AnimalConverter.toViewList(animals);
    }

    /**
     * 指定した動物園の販売済の動物一覧を取得し、AnimalViewリストで返却する
     * @param zv 動物園情報
     * @return 指定した動物園が販売した動物情報（販売中は除く）
     */
    public List<AnimalView> getMySold(ZooView zv) {
        List<Animal> animals = em.createNamedQuery(JpaConst.Q_ANI_GET_MY_SOLD, Animal.class)
                .setParameter(JpaConst.JPQL_PARM_ZOO, ZooConverter.toModel(zv))
                .setParameter(JpaConst.JPQL_PARM_SOLD_FLG, AttributeConst.SOLD_FLAG_TRUE.getIntegerValue())
                .getResultList();

        return AnimalConverter.toViewList(animals);
    }


    /**
     * 指定した動物園の販売中動物の件数を取得し、返却する
     * @param zv
     * @return 指定した動物園の販売中の動物件数
     */
    public long countMySelling(ZooView zv) {
        Long animalCount = em.createNamedQuery(JpaConst.Q_ANI_COUNT_MY_SELLING, Long.class)
                .setParameter(JpaConst.JPQL_PARM_ZOO, ZooConverter.toModel(zv))
                .setParameter(JpaConst.JPQL_PARM_SOLD_FLG, AttributeConst.SOLD_FLAG_FALSE.getIntegerValue())
                .getSingleResult();

        return animalCount;
    }

    /**
     * 指定した動物園の販売済動物の件数を取得し、返却する
     * @param zv
     * @return 指定した動物園の販売済の動物件数
     */
    public long countMySold(ZooView zv) {
        Long animalCount = em.createNamedQuery(JpaConst.Q_ANI_COUNT_MY_SOLD, Long.class)
                .setParameter(JpaConst.JPQL_PARM_ZOO, ZooConverter.toModel(zv))
                .setParameter(JpaConst.JPQL_PARM_SOLD_FLG, AttributeConst.SOLD_FLAG_TRUE.getIntegerValue())
                .getSingleResult();

        return animalCount;
    }


    /**
     * 指定した基本動物情報の販売動物一覧を取得し、返却する
     * @param bv 基本動物情報のデータ
     * @return 販売動物のリスト
     */
    public List<AnimalView> getByBaseId(AnimalBaseView bv) {
        List<Animal> animals = em.createNamedQuery(JpaConst.Q_ANI_GET_BY_BASE_ID, Animal.class)
                .setParameter(JpaConst.JPQL_PARM_ANIMALBASE, AnimalBaseConverter.toModel(bv))
                .getResultList();

        return AnimalConverter.toViewList(animals);
    }

    /**
     * 指定した基本動物情報の販売動物の件数を取得し、返却する
     * [基本動物情報のid, 左記の販売掲載動物の件数]でネストされたリストを作成
     * @return 販売動物の件数
     */
    public List<Object[]> getCountByBaseId() {
        List<Object[]> animalCount = em.createNamedQuery(JpaConst.Q_ANI_COUNT_GROUP_BY_BASE_ID, Object[].class)
                .getResultList();

        return animalCount;
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
    * 販売済みフラグを変更する
    * @param av
    */
    public void sold(AnimalView av) {

        //更新日時を現在時刻に設定
        LocalDateTime ldt = LocalDateTime.now();
        av.setUpdatedAt(ldt);
        updateInternal(av);
    }


    /**
     * 動物データを１件削除する
     * @param a 削除する動物データ
     */
    public void destory(Animal a) {
        //動物テーブルから引数のレコードを削除する

        em.getTransaction().begin();
        em.remove(a);
        em.getTransaction().commit();
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
