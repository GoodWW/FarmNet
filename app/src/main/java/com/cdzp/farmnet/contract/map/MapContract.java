package com.cdzp.farmnet.contract.map;

import com.cdzp.farmnet.bean.BaseEntity;

/**
 * 作者：张人文
 * 时间：2019/11/18 10:18
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public interface MapContract {
    interface Model {
        void excuteBack(String name, String code) throws Exception;
    }

    interface View<T extends BaseEntity> {
        void handlerBackResult(T t);
    }

    interface Presenter<T extends BaseEntity> {
        void requestLogin(String name, String code);

        void responseBackResult(T t);
    }
}
