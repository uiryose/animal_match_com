package services;

import java.util.List;

import actions.views.CustomerConverter;
import actions.views.CustomerView;
import constants.JpaConst;
import models.Customer;

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
     * idを条件にデータを1件取得し、Customerのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private Customer findOneInternal(int id) {
        Customer z = em.find(Customer.class, id);
        return z;
    }

    /**
     * 顧客データを全て取得し、CustomerViewのリストで返却する
     * @return 顧客データのリスト
     */
    public List<CustomerView> getAll() {

        List<Customer> customers = em.createNamedQuery(JpaConst.Q_CUST_GET_ALL, Customer.class)
                .getResultList();
        return CustomerConverter.toViewList(customers);
    }

}
