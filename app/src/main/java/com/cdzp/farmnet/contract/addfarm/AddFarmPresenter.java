package com.cdzp.farmnet.contract.addfarm;

import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.contract.login.LoginContract;
import com.cdzp.farmnet.ui.activity.LoginActivity;

/**
 * 作者：张人文
 * 时间：2019/11/18 14:44
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class AddFarmPresenter extends BaseViewPresenter<LoginActivity,AddFarmModel,LoginContract.Presenter> {
    @Override
    public LoginContract.Presenter getContract() {
        return new LoginContract.Presenter() {
            @Override
            public void requestLogin(String name, String code) {

            }

            @Override
            public void responseResult(BaseEntity baseEntity) {

            }
        };
    }

    @Override
    public AddFarmModel getModel() {
        return new AddFarmModel(this);
    }
}
