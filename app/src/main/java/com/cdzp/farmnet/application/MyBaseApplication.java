package com.cdzp.farmnet.application;


import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

import sad.zzq.com.selectaddressdemo.MyApplication;

/**
 * 作者：张人文
 * 时间：2019/12/12 17:40
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class MyBaseApplication extends MyApplication {
    @Override
    public void createStart() {

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }
}
