package com.cdzp.farmnet.contract.devicecontrol;

import com.cdzp.farmnet.bean.BaseEntity;

/**
 * 作者：张人文
 * 时间：2019/10/25 11:24
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public interface DeviceControlContract {
    //Model层需要做的事情
    interface Model {
        //Model层需要做一个执行登录的事情（他的子类来完成方法的具体实现）--------------2
        void executeLogin(String name, String pwd) throws Exception;
    }

    //View层需要做的事情   返回结果使用  T 泛型
    interface View<T extends BaseEntity> {
        //真实项目中，请求结果是以javabean的形式返回  用来刷新结果----------------4
        void handlerDeviceListResult(T t);//使用 T  泛型 不需要进行强转
    }

    //presenter需要做的事情  需要做两个事情
    interface Presenter<T extends BaseEntity> {
        //请求设备列表
        void requestDeviceList(String name, String pwd);

        // 2，结果的响应（接收到Mode层处理得结果，通通知View层刷新）------------------3
        void responseDeviceListResult(T t);
    }
}
