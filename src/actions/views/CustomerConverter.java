package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Customer;
import models.User;

public class CustomerConverter {


    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param cv CustomerViewのインスタンス
     * @return Customerのインスタンス
     */
    public static Customer toModel(CustomerView cv) {
        return new Customer(
                cv.getId(),
                UserConverter.toModel(cv.getUser()),
                cv.getCustomerName(),
                cv.getCreatedAt(),
                cv.getUpdatedAt());
    }

    /**
     * 新規でCustomerとUserを登録する際に、CustomerViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param cv CustomerViewのインスタンス
     * @param u persist直後のUserインスタンス
     * @return Customerのインスタンス
     */

    public static Customer toModelForCreate(CustomerView cv, User u) {

        return new Customer(
                cv.getId(),
                u,
                cv.getCustomerName(),
                cv.getCreatedAt(),
                cv.getUpdatedAt());
    }


    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param z Customerのインスタンス
     * @return CustomerViewのインスタンス
     */
    public static CustomerView toView(Customer z) {

        if(z == null) {
            return null;
        }

        return new CustomerView(
                z.getId(),
                UserConverter.toView(z.getUser()),
                z.getCustomerName(),
                z.getCreatedAt(),
                z.getUpdatedAt());
    }


    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<CustomerView> toViewList(List<Customer> list){
        List<CustomerView> cvl = new ArrayList<>();

        for(Customer c : list) {
            cvl.add(toView(c));
        }
        return cvl;

    }


    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param z DTOモデル(コピー先)
     * @param cv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Customer z, CustomerView cv) {
        z.setId(cv.getId());
        z.setUser(UserConverter.toModel(cv.getUser()));
        z.setCustomerName(cv.getCustomerName());
        z.setCreatedAt(cv.getCreatedAt());
        z.setUpdatedAt(cv.getUpdatedAt());
    }

}
