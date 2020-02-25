package com.cdzp.farmnet.contract.authentication;

import com.cdzp.farmnet.bean.UserInfo;

/**
 * 作者：张人文
 * 时间：2019/11/18 17:30
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public interface AuthenticationContract {
    interface Model {
    }

    interface View {
        void handlerResult(UserInfo t, int code);
        void handlerJudgeCodeResult(UserInfo t, int code);
    }

    interface Presenter {
        void requestCode(String phone);

        void judgeCode(String code, String phone);

        void responseResult(UserInfo t, int code);
        void responseJudgeCodeResult(UserInfo t, int code);
    }
}
