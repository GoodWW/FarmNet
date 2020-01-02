package com.cdzp.farmnet.contract.setpwd;

import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.ui.activity.SettingPWDActivity;

/**
 * 作者：张人文
 * 时间：2019/11/18 17:42
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class SetPWDPresenter extends BaseViewPresenter<SettingPWDActivity,SetPWDModel,SetPWDContract.Presenter> {

    @Override
    public SetPWDContract.Presenter getContract() {
        return new SetPWDContract.Presenter() {
            @Override
            public void requestSetPWD(String account, String pwd) {

            }

            @Override
            public void responseResult(BaseEntity baseEntity) {

            }
        };
    }

    @Override
    public SetPWDModel getModel() {
        return new SetPWDModel(this);
    }
}
