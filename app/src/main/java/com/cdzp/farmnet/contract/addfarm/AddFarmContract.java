package com.cdzp.farmnet.contract.addfarm;

import com.cdzp.farmnet.bean.BaseEntity;

/**
 * 作者：张人文
 * 时间：2019/11/18 10:18
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public interface AddFarmContract {
    interface Model {
        void excuteAdd(String name, String code) throws Exception;
    }

    interface View<T extends BaseEntity> {
        void handlerAddResult(T t);
    }

    interface Presenter<T extends BaseEntity> {
        void requestAdd(String name, String code);

        void responseAddResult(T t);
    }
}
