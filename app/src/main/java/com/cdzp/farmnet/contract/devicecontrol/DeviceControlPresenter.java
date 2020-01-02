package com.cdzp.farmnet.contract.devicecontrol;

import com.cdzp.farmnet.base.BaseFragmentPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.ui.fragment.HomeFragment;

/**
 * 作者：张人文
 * 时间：2019/10/25 14:21
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class DeviceControlPresenter extends BaseFragmentPresenter<HomeFragment, DeviceControlModel, DeviceControlContract.Presenter> {
    @Override
    public DeviceControlContract.Presenter getContract() {
        //纪要履行View给他的需求，又要分配工作给Model去完成这个需求
        return new DeviceControlContract.Presenter() {
            @Override
            public void requestDeviceList(String name, String pwd) {

            }

            @Override
            public void responseDeviceListResult(BaseEntity baseEntity) {

            }
        };

    }

    @Override
    public DeviceControlModel getModel() {
        return new DeviceControlModel(this);
    }
}
