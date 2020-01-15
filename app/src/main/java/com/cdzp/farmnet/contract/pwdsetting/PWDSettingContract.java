package com.cdzp.farmnet.contract.pwdsetting;

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
        void handlerSettingPWDResult(int code);
//        void handlerLoginOrRegisterResult(UserInfo userInfo, int flag);
    }

    interface Presenter {
        void requestSettingPWD(String phone, String password, String authCode);

        void responseSettingPWD(int code);
    }
}
