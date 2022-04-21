package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.User;

public class UserConverter {


    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param uv UserViewのインスタンス
     * @return Userのインスタンス
     */
    public static User toModel(UserView uv) {

        return new User(
                uv.getId(),
                uv.getCode(),
                uv.getUserFlag() == null
                        ? null
                        : uv.getUserFlag() == AttributeConst.USER_CUST.getIntegerValue()
                                ? JpaConst.USER_CUST
                                : JpaConst.USER_ZOO,
                uv.getPassword());
    }


    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param u Userのインスタンス
     * @return UserViewのインスタンス
     */
    public static UserView toView(User u) {

        if(u == null) {
            return null;
        }

        return new UserView(
                u.getId(),
                u.getCode(),
                u.getUserFlag() == null
                        ? null
                        : u.getUserFlag() == JpaConst.USER_CUST
                            ? AttributeConst.USER_CUST.getIntegerValue()
                            : AttributeConst.USER_ZOO.getIntegerValue(),
                u.getPassword());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<UserView> toViewList(List<User> list) {
        List<UserView> uvs = new ArrayList<>();

        for (User u : list) {
            uvs.add(toView(u));
        }
        return uvs;
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static void copyViewToModel(User u, UserView uv) {
        u.setId(uv.getId());
        u.setCode(uv.getCode());
        u.setUserFlag(uv.getUserFlag());
        u.setPassword(uv.getPassword());
    }
}
