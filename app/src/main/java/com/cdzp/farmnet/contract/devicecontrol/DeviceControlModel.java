package com.cdzp.farmnet.contract.devicecontrol;


import com.cdzp.farmnet.base.BaseFragmentModel;

/**
 * 作者：张人文
 * 时间：2019/10/25 14:20
 * 邮箱：479696877@QQ.COM
 * 描述：   接受到 P 层交给他的需求
 */
public class DeviceControlModel extends BaseFragmentModel<DeviceControlPresenter, DeviceControlContract.Model> {
    public DeviceControlModel(DeviceControlPresenter deviceControlPresenter) {
        super(deviceControlPresenter);
    }

    @Override
    public DeviceControlContract.Model getContract() {
        return new DeviceControlContract.Model() {
            @Override
            public void executeLogin(String name, String pwd) throws Exception {

            }
        };
    }
}
