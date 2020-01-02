package com.cdzp.farmnet.contract.setpwd;

import com.cdzp.farmnet.base.BaseViewModel;

/**
 * 作者：张人文
 * 时间：2019/11/18 17:42
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class SetPWDModel extends BaseViewModel<SetPWDPresenter, SetPWDContract.Model> {


    public SetPWDModel(SetPWDPresenter setPWDPresenter) {
        super(setPWDPresenter);
    }

    @Override
    public SetPWDContract.Model getContract() {
        return new SetPWDContract.Model() {
            @Override
            public void excuteSetPWD(String account, String pwd) throws Exception {

            }
        };
    }
}
