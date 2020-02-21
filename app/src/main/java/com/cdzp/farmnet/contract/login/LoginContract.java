package com.cdzp.farmnet.contract.login;

import com.cdzp.farmnet.bean.UserInfo;

/**
 * 作者：张人文
 * 时间：2019/11/18 10:18
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public interface LoginContract {
    interface Model {
//        void excuteLogin(String name, String code) throws Exception;
    }

    interface View {
        void handlerIsPhoneResult(int code);

        void handlerLoginOrRegisterResult(UserInfo userInfo, int flag, int responseCode);
    }

    interface Presenter {
        void requestLoginOrRegister(String name, String code, int flag);

        void responseLoginOrRegister(UserInfo userInfo, int flag, int responseCode);


        void requestIsPhone(String strIsPhone);

        void responseIsPhone(int code);
    }
}
