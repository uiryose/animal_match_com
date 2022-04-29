package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.ZooConverter;
import actions.views.ZooView;
import constants.JpaConst;
import models.Zoo;
import models.validators.ZooValidator;

/**
 * 動物園テーブルの操作に関わる処理を行うクラス
 */
public class ZooService extends ServiceBase {



    /**
     * idを条件に取得したデータをZooのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public ZooView findOne(int id) {
        Zoo z = findOneInternal(id);
        return ZooConverter.toView(z);
    }


    /**
     * Userのidを元に動物園情報を取得し、Viewモデルで返却する
     * @param id Userのid
     * @return ZooViewのインスタンス
     */
    public ZooView findOneByUserId(int id) {
        Zoo z = (Zoo) em.createNamedQuery(JpaConst.Q_ZOO_GET_BY_USER_ID, Zoo.class)
                .setParameter(JpaConst.JPQL_PARM_ID, id)
                .getSingleResult();

        return ZooConverter.toView(z);
    }


    /**
     * 画面から入力された動物園の登録内容を元にデータを1件作成し、動物園テーブルに登録する
     * @param zv
     * @return
     */
    public List<String> create(ZooView zv){
        List<String> errors = ZooValidator.validate(zv);
        if(errors.size()==0) {
            LocalDateTime ldt = LocalDateTime.now();
            zv.setCreatedAt(ldt);
            zv.setUpdatedAt(ldt);
            createInternal(zv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }


    /**
     * 画面から入力された動物園の登録内容を元に、動物園データを更新する
     * @param zv
     * @return
     */
    public List<String> update(ZooView zv){

        //バリデーションを行う
        List<String> errors = ZooValidator.validate(zv);

        if(errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            zv.setUpdatedAt(ldt);

            updateInternal(zv);
        }
        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;

    }



    /**
     * idを条件にデータを1件取得し、Zooのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private Zoo findOneInternal(int id) {
        Zoo z = em.find(Zoo.class, id);
        return z;
    }


    /**
     * 動物園データを1件登録する
     * @param zv 動物園データ
     */
    private void createInternal(ZooView zv) {

        em.getTransaction().begin();
        em.persist(ZooConverter.toModel(zv));
        em.getTransaction().commit();
    }


    /**
     * 動物園データを更新する
     * @param zv 画面から入力された動物園データ
     */
    private void updateInternal(ZooView zv) {
        em.getTransaction().begin();
        Zoo z = findOneInternal(zv.getId());
        ZooConverter.copyViewToModel(z, zv);
        em.getTransaction().commit();
    }



}
