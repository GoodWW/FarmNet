package com.cdzp.farmnet.contract.pwdsetting;

import com.cdzp.farmnet.bean.UserInfo;

/**
 * 作者：张人文
 * 时间：2019/11/18 10:18
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public interface PWDSettingContract {
    interface Model {
//        void excuteLogin(String name, String code) throws Exception;
    }

    interface View {
//        void handlerIsPhoneResult(boolean userInfo);
//        void handlerLoginOrRegisterResult(UserInfo userInfo, int flag);
    }

    interface Presenter {
        void requestSettingPWD(String phone, String password, String authCode);
        void responseSettingPWD(UserInfo userInfo, int flag);
    }
}
