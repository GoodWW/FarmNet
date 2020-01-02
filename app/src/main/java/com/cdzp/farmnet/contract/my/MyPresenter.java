package com.cdzp.farmnet.contract.my;

import com.cdzp.farmnet.base.BaseFragmentPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.ui.fragment.MyFragment;

/**
 * 作者：张人文
 * 时间：2019/11/20 11:04
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class MyPresenter extends BaseFragmentPresenter<MyFragment, MyModel, MyContract.Presenter> {
    @Override
    public MyContract.Presenter getContract() {
        return new MyContract.Presenter() {
            @Override
            public void requestInformation(String name, String pwd) {

            }

            @Override
            public void responseResult(BaseEntity baseEntity) {

            }
        };
    }

    @Override
    public MyModel getModel() {
        return new MyModel(this);
    }
}
