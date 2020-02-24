package com.cdzp.farmnet.contract.pwdlogin;

import com.cdzp.farmnet.bean.UserInfo;

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

    interface View {
        void handlerResult(UserInfo userInfo, int code);
    }

    interface Presenter {
        void requestLogin(String name, String pwd);

        void responseResult(UserInfo userInfo, int code);
    }
}
