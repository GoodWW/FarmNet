package com.cdzp.farmnet.contract.register;

import com.cdzp.farmnet.base.BaseViewModel;

/**
 * 作者：张人文
 * 时间：2019/11/18 17:42
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class RegisterModel extends BaseViewModel<RegisterPresenter, RegisterContract.Model> {


    public RegisterModel(RegisterPresenter registerPresenter) {
        super(registerPresenter);
    }

    @Override
    public RegisterContract.Model getContract() {
        return new RegisterContract.Model() {
            @Override
            public void excuteRegister(String account, String pwd) throws Exception {

            }
        };
    }
}
