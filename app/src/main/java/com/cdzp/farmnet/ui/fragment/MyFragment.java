package com.cdzp.farmnet.ui.fragment;

import android.view.View;
import android.widget.RelativeLayout;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseFragment;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.contract.my.MyContract;
import com.cdzp.farmnet.contract.my.MyPresenter;
import com.cdzp.farmnet.ui.activity.PersonalInformationActivity;

/**
 * 作者：张人文
 * 时间：2019/11/20 10:17
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class MyFragment extends BaseFragment<MyPresenter, MyContract.View> implements View.OnClickListener {
    private RelativeLayout rlOne;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlOne:
                startActivity(PersonalInformationActivity.class);
                break;
        }
    }

    @Override
    protected void initView(View mView) {
        rlOne = mView.findViewById(R.id.rlOne);
        rlOne.setOnClickListener(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_my;
    }

    @Override
    public MyContract.View getContract() {
        return new MyContract.View() {
            @Override
            public void handlerResult(BaseEntity baseEntity) {

            }
        };
    }

    @Override
    public MyPresenter getPresenter() {
        return new MyPresenter();
    }
}
