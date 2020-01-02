package com.cdzp.farmnet.contract.pwdlogin;

import com.cdzp.farmnet.base.BaseViewModel;

/**
 * 作者：张人文
 * 时间：2019/11/18 17:42
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class PWDModel extends BaseViewModel<PWDLoginPresenter, PWDLoginContract.Model> {


    public PWDModel(PWDLoginPresenter pwdLoginPresenter) {
        super(pwdLoginPresenter);
    }

    @Override
    public PWDLoginContract.Model getContract() {
        return new PWDLoginContract.Model() {
            @Override
            public void excuteLogin(String name, String pwd) throws Exception {

            }
        };
    }
}
