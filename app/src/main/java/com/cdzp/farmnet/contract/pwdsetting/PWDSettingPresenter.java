package com.cdzp.farmnet.contract.pwdsetting;

import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.ui.activity.PWDSettingActivity;

/**
 * 作者：张人文
 * 时间：2019/11/18 14:44
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class PWDSettingPresenter extends BaseViewPresenter<PWDSettingActivity, PWDSettingModel, PWDSettingContract.Presenter> {

    @Override
    public PWDSettingContract.Presenter getContract() {
        return new PWDSettingContract.Presenter() {
            @Override
            public void requestSettingPWD(String phone, String password, String authCode) {

            }

            @Override
            public void responseSettingPWD(UserInfo userInfo, int flag) {

            }
        };
    }

    @Override
    public PWDSettingModel getModel() {
        return new PWDSettingModel(this);
    }
}
