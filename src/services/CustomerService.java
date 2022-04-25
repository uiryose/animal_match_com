package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.CustomerConverter;
import actions.views.CustomerView;
import constants.JpaConst;
import models.Customer;
import models.validators.CustomerValidator;

/**
 * 顧客テーブルの操作に関わる処理を行うクラス
 */
public class CustomerService extends ServiceBase {


    /**
     * idを条件に取得したデータをCustomerのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public CustomerView findOne(int id) {
        Customer c = findOneInternal(id);
        return CustomerConverter.toView(c);
    }


    /**
     * Userのidを元に顧客情報を取得し、Viewモデルで返却する
     * @param id Userのid
     * @return CustomerViewのインスタンス
     */
    public CustomerView findOneByUserId(int id) {
        Customer c = (Customer) em.createNamedQuery(JpaConst.Q_CUST_GET_BY_USER_ID, Customer.class)
                .setParameter(JpaConst.JPQL_PARM_ID, id)
                .getSingleResult();

        return CustomerConverter.toView(c);
    }

    /**
     * 画面から入力された顧客の登録内容を元に、顧客データを更新する
     * @param cv
     * @return
     */
    public List<String> update(CustomerView cv){

        //バリデーションを行う
        List<String> errors = CustomerValidator.validate(cv);

        if(errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            cv.setUpdatedAt(ldt);

            updateInternal(cv);
        }
        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;

    }


    /**
     * idを条件にデータを1件取得し、Customerのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private Customer findOneInternal(int id) {
        Customer z = em.find(Customer.class, id);
        return z;
    }


    /**
     * 顧客データを更新する
     * @param cv 画面から入力された顧客データ
     */
    private void updateInternal(CustomerView cv) {
        em.getTransaction().begin();
        Customer z = findOneInternal(cv.getId());
        CustomerConverter.copyViewToModel(z, cv);
        em.getTransaction().commit();
    }



}
