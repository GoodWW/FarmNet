package com.cdzp.farmnet.contract.my;

import com.cdzp.farmnet.base.BaseFragmentModel;

/**
 * 作者：张人文
 * 时间：2019/11/20 11:04
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class MyModel extends BaseFragmentModel<MyPresenter, MyContract.Model> {
    public MyModel(MyPresenter myPresenter) {
        super(myPresenter);
    }

    @Override
    public MyContract.Model getContract() {
        return new MyContract.Model() {
            @Override
            public void executeInformation(String name, String pwd) throws Exception {

            }
        };
    }
}
