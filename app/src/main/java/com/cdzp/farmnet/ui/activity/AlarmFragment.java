package com.cdzp.farmnet.ui.activity;

import android.view.View;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseFragment;
import com.cdzp.farmnet.contract.alarm.AlarmContract;
import com.cdzp.farmnet.contract.alarm.AlarmPresenter;

/**
 * 作者：张人文
 * 时间：2020/1/7 15:55
 * 邮箱：479696877@QQ.COM
 * 描述：报警提示Fragment
 * */
public class AlarmFragment extends BaseFragment<AlarmPresenter, AlarmContract.View> {
    @Override
    protected void initView(View mView) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_alarm;
    }

    @Override
    public AlarmContract.View getContract() {
        return new AlarmContract.View() {
        };
    }

    @Override
    public AlarmPresenter getPresenter() {
        return new AlarmPresenter();
    }
}
