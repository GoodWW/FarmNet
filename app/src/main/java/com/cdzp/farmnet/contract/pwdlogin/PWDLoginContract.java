package com.cdzp.farmnet.contract.pwdlogin;

import com.cdzp.farmnet.bean.BaseEntity;

/**
 * 作者：张人文
 * 时间：2019/11/18 17:30
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public interface PWDLoginContract {
    interface Model {
        void excuteLogin(String name, String pwd) throws Exception;
    }

    interface View<T extends BaseEntity> {
        void handlerResult(T t);
    }

    interface Presenter<T extends BaseEntity> {
        void requestLogin(String name, String pwd);

        void responseResult(T t);
    }
}
