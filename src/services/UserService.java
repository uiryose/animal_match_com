package services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.NoResultException;

import actions.views.CustomerConverter;
import actions.views.CustomerView;
import actions.views.UserConverter;
import actions.views.UserView;
import actions.views.ZooConverter;
import actions.views.ZooView;
import constants.JpaConst;
import models.Customer;
import models.User;
import models.Zoo;
import models.validators.CustomerValidator;
import models.validators.UserValidator;
import models.validators.ZooValidator;
import utils.EncryptUtil;

/**
 * ユーザーテーブルの操作に関わる処理を行うクラス
 */
public class UserService extends ServiceBase {

    /**
     * コードとパスワードを条件に取得したデータをUserViewのインスタンスで返却する
     * @param code ユーザーコード
     * @param plainPass パスワード文字列
     * @param pepper pepper文字列
     * @return 取得データのインスタンス、取得できない場合はnull
     */
    public UserView findOne(String code, String plainPass, String pepper) {

        User u = null;
        try {
            //パスワードのハッシュ化
            String pass = EncryptUtil.getPasswordEncrypt(plainPass, pepper);

            //ユーザーコードとハッシュ化済パスワードを条件に未削除のユーザーを1件取得する
            u = em.createNamedQuery(JpaConst.Q_USER_GET_BY_CODE_AND_PASS, User.class)
                    .setParameter(JpaConst.JPQL_PARM_CODE, code)
                    .setParameter(JpaConst.JPQL_PARM_PASSWORD, pass)
                    .getSingleResult();

        } catch (NoResultException ex) {
        }

        return UserConverter.toView(u);

    }

    /**
     * idを条件に取得したデータをUserViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public UserView findOne(int id) {
        User u = findOneInternal(id);
        return UserConverter.toView(u);
    }


    /**
     * コードを条件に該当するデータの件数を取得し、返却する
     * @param code ユーザーコード
     * @return 該当するデータの件数
     */
    public long countByCode(String code) {

        //指定したユーザーコードを保持するユーザーの件数を取得する。(通常は0～1件)
        long user_count = (long) em.createNamedQuery(JpaConst.Q_USER_COUNT_RESISTERED_BY_CODE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_CODE, code)
                .getSingleResult();
        return user_count;
    }


    /**
     * 画面から入力された登録内容を元にデータを1件作成し、ユーザーと動物園テーブルに登録する(Mapで実装)
     * @param uv 画面から入力されたユーザーの登録内容
     * @param pepper pepper文字列
     * @param zv 画面から入力された動物園の登録内容
     * @return バリデーションエラーのリスト
     */
    public Map<Integer, Object> create(UserView uv, String pepper, ZooView zv){

        //パスワードをハッシュ化して設定
        String pass = EncryptUtil.getPasswordEncrypt(uv.getPassword(), pepper);
        uv.setPassword(pass);

        LocalDateTime ldt = LocalDateTime.now();
        zv.setCreatedAt(ldt);
        zv.setUpdatedAt(ldt);

        //登録内容のバリデーションを行う
        List<String> userErrors = UserValidator.validate(this, uv, true, true);
        List<String> zooErrors = ZooValidator.validate(zv);
        List<String> errors = Stream.concat(userErrors.stream(), zooErrors.stream()).collect(Collectors.toList());

        HashMap<Integer, Object> userIdAndErrorsap = new HashMap<Integer, Object>();
        //２番目にエラーリストを格納
        userIdAndErrorsap.put(2, errors);

        if(errors.size() == 0) {
            //バリデーションエラーがなければ、ユーザー登録行い、mapの１番目にユーザーIDを格納
            Integer userId = createInternal(uv,zv);
            userIdAndErrorsap.put(1, userId);
        }
        //返却（エラーがなければuserIdと0件の空リスト）
        //返却（エラーがあればエラーリストのみ）
        return userIdAndErrorsap;
    }


    /**
     * 画面から入力された登録内容を元にデータを1件作成し、ユーザーと顧客テーブルに登録する(HashMapで実装)
     * @param uv 画面から入力されたユーザーの登録内容
     * @param pepper pepper文字列
     * @param cv 画面から入力された顧客の登録内容
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public HashMap create(UserView uv, String pepper, CustomerView cv){

        //パスワードをハッシュ化して設定
        String pass = EncryptUtil.getPasswordEncrypt(uv.getPassword(), pepper);
        uv.setPassword(pass);

        LocalDateTime ldt = LocalDateTime.now();
        cv.setCreatedAt(ldt);
        cv.setUpdatedAt(ldt);

        //登録内容のバリデーションを行う
        List<String> userErrors = UserValidator.validate(this, uv, true, true);
        List<String> customerErrors = CustomerValidator.validate(cv);

        List<String> errors = new ArrayList<String>();
        errors = Stream.concat(userErrors.stream(), customerErrors.stream()).collect(Collectors.toList());

        //ユーザーIDとエラーをmapに格納するための準備
        HashMap userIdAndErrors = new HashMap();

        if (errors.size() == 0) {
            //バリデーションエラーがなければデータを登録する

            //mapの１番目にユーザーIDをリストとして格納
            userIdAndErrors.put(1, createInternal(uv, cv));
            //mapの２番目にエラーリスト(0件の空リスト)を格納
            userIdAndErrors.put(2, errors);

        } else {
            //バリデーションエラーがある場合はエラーリストのみmapに格納
            userIdAndErrors.put(2, errors);
        }

        //返却（エラーがなければuserIdと0件の空リスト）
        //返却（エラーがあればエラーリストのみ）
        return userIdAndErrors;
    }


    /**
     * 画面から入力されたユーザーと顧客の更新内容を元にデータを作成し、ユーザーテーブルと顧客テーブルを更新する
     * @param uv 画面から入力されたユーザーの登録内容
     * @param pepper pepper文字列
     * @return バリデーションや更新処理中に発生したエラーのリスト
     */
    public List<String> update(UserView uv, String pepper, CustomerView cv){

        //idを条件に登録済のユーザー情報を取得する
        UserView savedUser = findOne(uv.getId());

        boolean validateCode = false;
        if(!savedUser.getCode().equals(uv.getCode())) {
            //ユーザーコードを更新する場合

            //ユーザーコードのバリデーションを行う
            validateCode = true;
            //変更後のコードを設定する
            savedUser.setCode(uv.getCode());
        }

        boolean validatePass = false;
        if(uv.getPassword() != null && !uv.getPassword().equals("")) {
            //パスワードに入力がある場合

            //パスワードについてのバリデーションを行う
            validatePass = true;
            //変更後のパスワードをハッシュ化し設定する
            savedUser.setPassword(EncryptUtil.getPasswordEncrypt(uv.getPassword(), pepper));

        }

        //更新内容についてバリデーションを行う
        List<String> userErrors = UserValidator.validate(this, savedUser, validateCode, validatePass);
        List<String> customerErrors = CustomerValidator.validate(cv);
        List<String> errors = Stream.concat(userErrors.stream(), customerErrors.stream()).collect(Collectors.toList());


        //バリデーションエラーがなければデータを更新する
        if (errors.size() == 0) {

            LocalDateTime ldt = LocalDateTime.now();
            cv.setUpdatedAt(ldt);

            updateInternal(savedUser, cv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }


    /**
     * 画面から入力されたユーザーと動物園の更新内容を元にデータを作成し、ユーザーテーブルと動物園テーブルを更新する
     * @param uv 画面から入力されたユーザーの登録内容
     * @param pepper pepper文字列
     * @return バリデーションや更新処理中に発生したエラーのリスト
     */
    public List<String> update(UserView uv, String pepper, ZooView zv){

        //idを条件に登録済のユーザー情報を取得する
        UserView savedUser = findOne(uv.getId());

        boolean validateCode = false;
        if(!savedUser.getCode().equals(uv.getCode())) {
            //ユーザーコードを更新する場合

            //ユーザーコードのバリデーションを行う
            validateCode = true;
            //変更後のコードを設定する
            savedUser.setCode(uv.getCode());
        }

        boolean validatePass = false;
        if(uv.getPassword() != null && !uv.getPassword().equals("")) {
            //パスワードに入力がある場合

            //パスワードについてのバリデーションを行う
            validatePass = true;
            //変更後のパスワードをハッシュ化し設定する
            savedUser.setPassword(EncryptUtil.getPasswordEncrypt(uv.getPassword(), pepper));

        }

        //更新内容についてバリデーションを行う
        List<String> userErrors = UserValidator.validate(this, savedUser, validateCode, validatePass);
        List<String> zooErrors = ZooValidator.validate(zv);
        List<String> errors = Stream.concat(userErrors.stream(), zooErrors.stream()).collect(Collectors.toList());


        //バリデーションエラーがなければデータを更新する
        if (errors.size() == 0) {

            LocalDateTime ldt = LocalDateTime.now();
            zv.setUpdatedAt(ldt);

            updateInternal(savedUser, zv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }


    /**
     * 論理削除を行う
     * @param id
     */
    public void destroy(Integer id) {

        //idを条件に登録済みのユーザー情報を取得する
        UserView savedUser = findOne(id);

        //論理削除フラグを立てる
        savedUser.setDeleteFlag(JpaConst.USER_DEL_TRUE);

        //更新作業を行う
        updateInternal(savedUser);

    }


    /**
     * ユーザーコードとパスワードを条件に検索し、データが取得できるかどうかで認証する
     * @param code ユーザーコード
     * @param plainPass パスワード
     * @param pepper pepper文字列
     * @return 認証結果を返却す(成功:true 失敗:false)
     */
    public Boolean validateLogin(String code, String plainPass, String pepper) {

        boolean isValidUser = false;
        if(code != null && !code.equals("") && plainPass != null && !plainPass.equals("")) {

            UserView uv = findOne(code, plainPass, pepper);

            if(uv !=null && uv.getId() != null) {

                //データが取得できた場合、認証成功
                isValidUser = true;
            }
        }

        //認証結果を返却する
        return isValidUser;

    }


    /**
     * idを条件にデータを1件取得し、Userのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private User findOneInternal(int id) {
        User u = em.find(User.class, id);
        return u;
    }


    /**
     * ユーザーデータと動物園データを同時に1件ずつ登録する
     * @param uv ユーザーデータ
     * @return 登録結果(成功:true 失敗:false)
     */
    private Integer createInternal(UserView uv, ZooView zv) {

        em.getTransaction().begin();
        User u = UserConverter.toModel(uv);
        em.persist(u);
        em.persist(ZooConverter.toModelForCreate(zv, u));
        em.getTransaction().commit();

        return(u.getId());
    }

    /**
     * ユーザーデータと顧客データを同時に1件ずつ登録する
     * @param uv ユーザーデータ
     * @return 登録結果(成功:true 失敗:false)
     */
    private Integer createInternal(UserView uv, CustomerView cv) {

        em.getTransaction().begin();
        User u = UserConverter.toModel(uv);
        em.persist(u);
        em.persist(CustomerConverter.toModelForCreate(cv, u));
        em.getTransaction().commit();

        return(u.getId());
    }


    /**
     * ユーザーデータを1件更新する
     * @param uv
     * @param cv
     */
    private void updateInternal(UserView uv) {

        em.getTransaction().begin();
        //取得したDTOモデルを変更することで、commit時にDBへ反映される
        User u = findOneInternal(uv.getId());
        UserConverter.copyViewToModel(u, uv);
        em.getTransaction().commit();
    }


    /**
     * ユーザーデータを顧客データを同時に1件ずつ更新する
     * @param uv 画面から入力されたユーザーの登録内容
     * @return 登録結果(成功:true 失敗:false)
     */
    private void updateInternal(UserView uv, CustomerView cv) {

        em.getTransaction().begin();

        //取得したDTOモデルを変更することで、commit時にDBへ反映される
        User u = findOneInternal(uv.getId());
        UserConverter.copyViewToModel(u, uv);

        Customer c = findOneCustomerInternal(cv.getId());
        CustomerConverter.copyViewToModel(c, cv);

        em.getTransaction().commit();
    }

    /**
     * ユーザーデータを動物園データを同時に1件ずつ更新する
     * @param uv 画面から入力されたユーザーの登録内容
     * @return 登録結果(成功:true 失敗:false)
     */
    private void updateInternal(UserView uv, ZooView zv) {

        em.getTransaction().begin();

        //取得kしたDTOモデルを変更することで、commit時にDBへ反映される
        User u = findOneInternal(uv.getId());
        UserConverter.copyViewToModel(u, uv);

        Zoo z = findOneZooInternal(zv.getId());
        ZooConverter.copyViewToModel(z, zv);

        em.getTransaction().commit();
    }

    /**
     * idを条件にデータを1件取得し、Customerのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private Customer findOneCustomerInternal(int id) {
        Customer c = em.find(Customer.class, id);
        return c;
    }

    /**
     * idを条件にデータを1件取得し、Zooのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private Zoo findOneZooInternal(int id) {
        Zoo z = em.find(Zoo.class, id);
        return z;
    }


}
