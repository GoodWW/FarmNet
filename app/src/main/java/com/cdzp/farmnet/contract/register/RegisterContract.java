package com.cdzp.farmnet.contract.register;

import com.cdzp.farmnet.bean.BaseEntity;

/**
 * 作者：张人文
 * 时间：2019/11/18 17:30
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public interface RegisterContract {
    interface Model {
        void excuteRegister(String account, String pwd) throws Exception;
    }

    interface View<T extends BaseEntity> {
        void handlerRegisterResult(T t);
    }

    interface Presenter<T extends BaseEntity> {
        void requestRegister(String account, String pwd);

        void responseRegisterResult(T t);
    }
}
