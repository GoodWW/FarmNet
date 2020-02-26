package com.cdzp.farmnet.contract.homeactivity;


import com.cdzp.farmnet.base.BaseViewModel;

/**
 * 作者：张人文
 * 时间：2019/10/25 14:20
 * 邮箱：479696877@QQ.COM
 * 描述：   接受到 P 层交给他的需求
 */
public class HomeActivityModel extends BaseViewModel<HomeActivityPresenter, HomeActivityContract.Model> {

    public HomeActivityModel(HomeActivityPresenter homeActivityPresenter) {
        super(homeActivityPresenter);
    }

    @Override
    public HomeActivityContract.Model getContract() {
        return new HomeActivityContract.Model() {
        };
    }
}
