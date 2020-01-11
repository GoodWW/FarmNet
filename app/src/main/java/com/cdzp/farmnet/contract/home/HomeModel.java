package com.cdzp.farmnet.contract.home;


import com.cdzp.farmnet.base.BaseFragmentModel;

/**
 * 作者：张人文
 * 时间：2019/10/25 14:20
 * 邮箱：479696877@QQ.COM
 * 描述：   接受到 P 层交给他的需求
 */
public class HomeModel extends BaseFragmentModel<HomePresenter, HomeContract.Model> {
    public HomeModel(HomePresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    public HomeContract.Model getContract() {
        return new HomeContract.Model() {
            @Override
            public void executeLogin(String name, String pwd) throws Exception {
//                //省略网络请求
//                if ("admin".equalsIgnoreCase(name) && "123".equalsIgnoreCase(pwd)) {
//                    p.getContract().responseResult(new UserInfo("智棚","张人文"));
//                } else {
//                    p.getContract().responseResult(null);
//                }
            }
        };
    }
}
