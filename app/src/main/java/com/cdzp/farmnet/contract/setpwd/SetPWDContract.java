package com.cdzp.farmnet.contract.setpwd;

import com.cdzp.farmnet.bean.BaseEntity;

/**
 * 作者：张人文
 * 时间：2019/11/18 17:30
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public interface SetPWDContract {
    interface Model {
        void excuteSetPWD(String account, String pwd) throws Exception;
    }

    interface View<T extends BaseEntity> {
        void handlerResult(T t);
    }

    interface Presenter<T extends BaseEntity> {
        void requestSetPWD(String account, String pwd);

        void responseResult(T t);
    }
}
