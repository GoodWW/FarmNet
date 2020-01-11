package com.cdzp.farmnet.contract.addfarm;

import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.ui.activity.AddFrameActivity;

/**
 * 作者：张人文
 * 时间：2019/11/18 14:44
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class AddFarmPresenter extends BaseViewPresenter<AddFrameActivity,AddFarmModel,AddFarmContract.Presenter> {
    @Override
    public AddFarmContract.Presenter getContract() {
        return new AddFarmContract.Presenter(){

        };
    }

    @Override
    public AddFarmModel getModel() {
        return new AddFarmModel(this);
    }
}
