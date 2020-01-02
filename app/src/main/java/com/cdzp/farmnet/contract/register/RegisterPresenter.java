package com.cdzp.farmnet.contract.register;

import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.ui.activity.RegisterActivity;

/**
 * 作者：张人文
 * 时间：2019/11/18 17:42
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class RegisterPresenter extends BaseViewPresenter<RegisterActivity,RegisterModel,RegisterContract.Presenter> {

    @Override
    public RegisterContract.Presenter getContract() {
        return new RegisterContract.Presenter() {
            @Override
            public void requestRegister(String account, String pwd) {

            }

            @Override
            public void responseRegisterResult(BaseEntity baseEntity) {

            }
        };
    }

    @Override
    public RegisterModel getModel() {
        return new RegisterModel(this);
    }
}
