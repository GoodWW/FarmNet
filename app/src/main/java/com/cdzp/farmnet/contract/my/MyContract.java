package com.cdzp.farmnet.contract.my;

import com.cdzp.farmnet.bean.BaseEntity;

/**
 * 作者：张人文
 * 时间：2019/10/25 11:24
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public interface MyContract {
    interface Model {
        //获取个人信息
        void executeInformation (String name, String pwd) throws Exception;
    }

    interface View<T extends BaseEntity> {
        void handlerResult(T t);
    }

    interface Presenter<T extends BaseEntity> {
        //获取个人信息
        void requestInformation(String name, String pwd);

        void responseResult(T t);
    }
}
