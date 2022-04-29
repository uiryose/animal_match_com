package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.AnimalBaseConverter;
import actions.views.AnimalBaseView;
import actions.views.AnimalConverter;
import actions.views.AnimalView;
import constants.JpaConst;
import models.AnimalBase;
import models.validators.AnimalValidator;

/**
 * AnimalBaseテーブルの操作にかかわる処理を行うクラス
 *
 */
public class AnimalBaseService extends ServiceBase {


    /**
     * 指定されたページ数の一覧画面に表示する基本動物情報データを取得し、AnimalaBaseViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<AnimalBaseView> getAllPerPage(int page) {

        List<AnimalBase> animalBases = em.createNamedQuery(JpaConst.Q_BASE_GET_ALL, AnimalBase.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return AnimalBaseConverter.toViewList(animalBases);
    }


    /**
     * 全ての基本動物情報データを取得する
     * @return
     */
    public List<AnimalBaseView> getAllOrderByName(){

        List<AnimalBase> animalBases = em.createNamedQuery(JpaConst.Q_BASE_GET_ALL, AnimalBase.class)
                .getResultList();
        return AnimalBaseConverter.toViewList(animalBases);
    }




    /**
     * 基本動物情報テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long animalBases_count = (long) em.createNamedQuery(JpaConst.Q_BASE_COUNT, Long.class)
                .getSingleResult();
        return animalBases_count;
    }

    /**
     * idを条件に取得したデータをAnimalaBaseViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public AnimalBaseView findOne(int id) {
        return AnimalBaseConverter.toView(findOneInternal(id));
    }


    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private AnimalBase findOneInternal(int id) {
        return em.find(AnimalBase.class, id);
    }


    /**
     * 画面から入力された動物登録内容を元にデータを1件作成し、動物テーブルに登録する
     * @param av
     * @return
     */
    public List<String> create(AnimalView av) {

        List<String> errors = AnimalValidator.validate(av);
        if(errors.size()==0) {
            LocalDateTime ldt = LocalDateTime.now();
            av.setCreatedAt(ldt);
            av.setUpdatedAt(ldt);
            createInternal(av);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
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

}
