package com.cdzp.farmnet.contract.addfarm;

import com.cdzp.farmnet.base.BaseViewModel;

/**
 * 作者：张人文
 * 时间：2019/11/18 14:44
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class AddFarmModel extends BaseViewModel<AddFarmPresenter,AddFarmContract.Model> {
    public AddFarmModel(AddFarmPresenter addFarmPresenter) {
        super(addFarmPresenter);
    }

    @Override
    public AddFarmContract.Model getContract() {
        return new AddFarmContract.Model() {

        };
    }
}
