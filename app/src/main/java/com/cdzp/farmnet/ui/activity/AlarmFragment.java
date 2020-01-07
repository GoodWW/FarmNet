package com.cdzp.farmnet.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseFragment;
import com.cdzp.farmnet.contract.alarm.AlarmContract;
import com.cdzp.farmnet.contract.alarm.AlarmPresenter;

/**
 * 作者：张人文
 * 时间：2020/1/7 15:55
 * 邮箱：479696877@QQ.COM
 * 描述：报警提示Fragment
 */
public class AlarmFragment extends BaseFragment<AlarmPresenter, AlarmContract.View> implements View.OnClickListener {
    private TextView tvHistorical, tvCurrent;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvHistorical:
                tvHistorical.setTextColor(mContext.getResources().getColor(R.color.white));
                tvCurrent.setTextColor(mContext.getResources().getColor(R.color.font_dark));
                tvHistorical.setBackground(mContext.getDrawable(R.drawable.bg_text_blue));

                tvCurrent.setBackground(mContext.getDrawable(R.drawable.bg_text_white));
                break;
            case R.id.tvCurrent:
                tvCurrent.setTextColor(mContext.getResources().getColor(R.color.white));
                tvHistorical.setTextColor(mContext.getResources().getColor(R.color.font_dark));
                tvHistorical.setBackground(mContext.getDrawable(R.drawable.bg_text_white));
                tvCurrent.setBackground(mContext.getDrawable(R.drawable.bg_text_blue));
                break;

        }

    }

    @Override
    protected void initView(View mView) {
        tvHistorical = mView.findViewById(R.id.tvHistorical);
        tvCurrent = mView.findViewById(R.id.tvCurrent);
        tvCurrent.setOnClickListener(this);
        tvHistorical.setOnClickListener(this);

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
