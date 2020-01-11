package com.cdzp.farmnet.contract.login;

import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.bean.UserInfo;

/**
 * 作者：张人文
 * 时间：2019/11/18 10:18
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public interface LoginContract {
    interface Model {
        void excuteLogin(String name, String code) throws Exception;
    }

    interface View<T extends BaseEntity> {
        void handlerResult(T t);
        void handlerIsPhoneResult(BaseEntity<UserInfo> t);
    }

    interface Presenter<T extends BaseEntity> {
        void requestLogin(String name, String code);

        void responseResult(T t);

        void requestIsPhone(String strIsPhone);
        void responseIsPhone(T userInfo);
    }
}
