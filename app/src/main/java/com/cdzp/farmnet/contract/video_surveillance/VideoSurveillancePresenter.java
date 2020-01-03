package com.cdzp.farmnet.contract.video_surveillance;

import com.cdzp.farmnet.base.BaseFragmentPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.contract.my.MyContract;
import com.cdzp.farmnet.contract.my.MyModel;
import com.cdzp.farmnet.ui.fragment.MyFragment;
import com.cdzp.farmnet.ui.fragment.VideoSurveillanceFragment;

/**
 * 作者：张人文
 * 时间：2019/11/20 11:04
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class VideoSurveillancePresenter extends BaseFragmentPresenter<VideoSurveillanceFragment, VideoSurveillanceModel, VideoSurveillanceContract.Presenter> {

    @Override
    public VideoSurveillanceContract.Presenter getContract() {
        return new VideoSurveillanceContract.Presenter() {
        };
    }

    @Override
    public VideoSurveillanceModel getModel() {
        return new VideoSurveillanceModel(this);
    }
}
