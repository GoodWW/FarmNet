package com.cdzp.farmnet.contract.authentication;

import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.ui.activity.AuthenticationActivity;

/**
 * 作者：张人文
 * 时间：2019/11/18 14:44
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class AuthenticationPresenter extends BaseViewPresenter<AuthenticationActivity,AuthenticationModel,AuthenticationContract.Presenter> {
    @Override
    public AuthenticationContract.Presenter getContract() {
        return new AuthenticationContract.Presenter() {
            @Override
            public void requestCode(String phone) {

            }

            @Override
            public void responseResult(BaseEntity baseEntity) {

            }
        };

    }

    @Override
    public AuthenticationModel getModel() {
        return new AuthenticationModel(this);
    }
}
