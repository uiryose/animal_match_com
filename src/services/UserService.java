package services;

import java.util.List;

import javax.persistence.NoResultException;

import actions.views.UserConverter;
import actions.views.UserView;
import constants.JpaConst;
import models.User;
import models.validators.UserValidator;
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
     * 画面から入力されたユーザーの登録内容を元にデータを1件作成し、ユーザーテーブルに登録する
     * @param ev 画面から入力されたユーザーの登録内容
     * @param pepper pepper文字列
     * @return バリデーションや登録処理中に発生したエラーのリスト
     */
    public List<String> create(UserView uv, String pepper){

        //パスワードをハッシュ化して設定
        String pass = EncryptUtil.getPasswordEncrypt(uv.getPassword(), pepper);
        uv.setPassword(pass);

        //登録内容のバリデーションを行う
        List<String> errors = UserValidator.validate(this, uv, true, true);

        //バリデーションエラーがなければデータを登録する
        if(errors.size() == 0) {
            createInternal(uv);
        }

        //エラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }


    /**
     * 画面から入力されたユーザーの更新内容を元にデータを1件作成し、ユーザーテーブルを更新する
     * @param uv 画面から入力されたユーザーの登録内容
     * @param pepper pepper文字列
     * @return バリデーションや更新処理中に発生したエラーのリスト
     */
    public List<String> update(UserView uv, String pepper){

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
        List<String> errors = UserValidator.validate(this, savedUser, validateCode, validatePass);

        //バリデーションエラーがなければデータを更新する
        if (errors.size() == 0) {
            updateInternal(savedUser);
        }

        //エラーを返却（エラーがなければ0件の空リスト）
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
     * idを条件にデータを1件取得し、Userのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private User findOneInternal(int id) {
        User e = em.find(User.class, id);
        return e;
    }


    /**
     * ユーザーデータを1件登録する
     * @param uv ユーザーデータ
     * @return 登録結果(成功:true 失敗:false)
     */
    private void createInternal(UserView uv) {

        em.getTransaction().begin();
        em.persist(UserConverter.toModel(uv));
        em.getTransaction().commit();

    }


    /**
     * ユーザーデータを更新する
     * @param uv 画面から入力されたユーザーの登録内容
     */
    private void updateInternal(UserView uv) {

        em.getTransaction().begin();
        User u = findOneInternal(uv.getId());
        UserConverter.copyViewToModel(u, uv);
        em.getTransaction().commit();
    }




}
