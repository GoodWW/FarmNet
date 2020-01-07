package com.cdzp.farmnet.contract.alarm;

import com.cdzp.farmnet.base.BaseFragmentPresenter;
import com.cdzp.farmnet.contract.video_surveillance.VideoSurveillanceContract;
import com.cdzp.farmnet.contract.video_surveillance.VideoSurveillanceModel;
import com.cdzp.farmnet.ui.activity.AlarmFragment;
import com.cdzp.farmnet.ui.fragment.VideoSurveillanceFragment;

/**
 * 作者：张人文
 * 时间：2019/11/20 11:04
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class AlarmPresenter extends BaseFragmentPresenter<AlarmFragment, AlarmModel, AlarmContract.Presenter> {

    @Override
    public AlarmContract.Presenter getContract() {
        return new AlarmContract.Presenter() {
        };
    }

    @Override
    public AlarmModel getModel() {
        return new AlarmModel(this);
    }
}
