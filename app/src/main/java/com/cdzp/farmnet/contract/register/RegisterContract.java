package com.cdzp.farmnet.contract.register;

import com.cdzp.farmnet.bean.UserInfo;

/**
 * 作者：张人文
 * 时间：2019/11/18 17:30
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public interface RegisterContract {
    interface Model {
    }

    interface View {
        void handlerRegisterResult(UserInfo t,int code);
    }

    interface Presenter {
        void requestRegister(String account, String pwd);

        void responseRegisterResult(UserInfo t,int code);
    }
}
