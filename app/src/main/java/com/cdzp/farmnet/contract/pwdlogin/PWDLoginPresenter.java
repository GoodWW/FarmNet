package com.cdzp.farmnet.contract.pwdlogin;

import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.ui.activity.PWDLoginActivity;

/**
 * 作者：张人文
 * 时间：2019/11/18 17:42
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class PWDLoginPresenter extends BaseViewPresenter<PWDLoginActivity,PWDModel,PWDLoginContract.Presenter> {
    @Override
    public PWDLoginContract.Presenter getContract() {
        return new PWDLoginContract.Presenter() {
            @Override
            public void requestLogin(String name, String pwd) {

            }

            @Override
            public void responseResult(BaseEntity baseEntity) {

            }
        };
    }

    @Override
    public PWDModel getModel() {
        return new PWDModel(this);
    }
}
