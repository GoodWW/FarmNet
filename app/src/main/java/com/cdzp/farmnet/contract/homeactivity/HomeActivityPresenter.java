package com.cdzp.farmnet.contract.homeactivity;

import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.ui.activity.HomeActivity;

/**
 * 作者：张人文
 * 时间：2019/10/25 14:21
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class HomeActivityPresenter extends BaseViewPresenter<HomeActivity, HomeActivityModel, HomeActivityContract.Presenter> {

    @Override
    public HomeActivityContract.Presenter getContract() {
        return new HomeActivityContract.Presenter() {
        };
    }

    @Override
    public HomeActivityModel getModel() {
        return new HomeActivityModel(this);
    }
}
