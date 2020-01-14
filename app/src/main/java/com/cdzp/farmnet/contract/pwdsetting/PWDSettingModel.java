package com.cdzp.farmnet.contract.pwdsetting;

import com.cdzp.farmnet.base.BaseViewModel;

/**
 * 作者：张人文
 * 时间：2019/11/18 14:44
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class PWDSettingModel extends BaseViewModel<PWDSettingPresenter,PWDSettingContract.Model> {
    public PWDSettingModel(PWDSettingPresenter pwdSettingPresenter) {
        super(pwdSettingPresenter);
    }

    @Override
    public PWDSettingContract.Model getContract() {
        return new PWDSettingContract.Model() {
        };
    }
}
