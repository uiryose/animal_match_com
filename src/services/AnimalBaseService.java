package services;

import java.util.List;

import actions.views.AnimalBaseConverter;
import actions.views.AnimalBaseView;
import constants.JpaConst;
import models.AnimalBase;

/**
 * AnimalBaseテーブルの操作にかかわる処理を行うクラス
 *
 */
public class AnimalBaseService extends ServiceBase {


//employeeが絡むのはいらない？
//    /**
//     * 指定した従業員が作成した基本動物情報データを、指定されたページ数の一覧画面に表示する分取得しAnimalaBaseViewのリストで返却する
//     * @param employee 従業員
//     * @param page ページ数
//     * @return 一覧画面に表示するデータのリスト
//     */
//    public List<AnimalBaseView> getMinePerPage(EmployeeView employee, int page) {
//
//        List<AnimalBase> animalBases = em.createNamedQuery(JpaConst.Q_BASE_GET_ALL_MINE, AnimalBase.class)
//                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
//                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
//                .setMaxResults(JpaConst.ROW_PER_PAGE)
//                .getResultList();
//        return AnimalBaseConverter.toViewList(animalBases);
//    }
//
//    /**
//     * 指定した従業員が作成した基本動物情報データの件数を取得し、返却する
//     * @param employee
//     * @return 基本動物情報データの件数
//     */
//    public long countAllMine(EmployeeView employee) {
//
//        long count = (long) em.createNamedQuery(JpaConst.Q_BASE_COUNT_ALL_MINE, Long.class)
//                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
//                .getSingleResult();
//
//        return count;
//    }
//

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


}
