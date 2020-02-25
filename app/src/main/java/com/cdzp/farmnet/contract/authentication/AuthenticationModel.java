package com.cdzp.farmnet.contract.authentication;

import com.cdzp.farmnet.base.BaseViewModel;

/**
 * 作者：张人文
 * 时间：2019/11/18 14:44
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class AuthenticationModel extends BaseViewModel<AuthenticationPresenter,AuthenticationContract.Model> {

    public AuthenticationModel(AuthenticationPresenter authenticationPresenter) {
        super(authenticationPresenter);
    }

    @Override
    public AuthenticationContract.Model getContract() {
        return new AuthenticationContract.Model() {
        };
    }
}
