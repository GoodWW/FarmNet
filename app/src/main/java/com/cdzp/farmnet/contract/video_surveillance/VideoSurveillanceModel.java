package com.cdzp.farmnet.contract.video_surveillance;

import com.cdzp.farmnet.base.BaseFragmentModel;

/**
 * 作者：张人文
 * 时间：2019/11/20 11:04
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class VideoSurveillanceModel extends BaseFragmentModel<VideoSurveillancePresenter, VideoSurveillanceContract.Model> {

    public VideoSurveillanceModel(VideoSurveillancePresenter videoSurveillancePresenter) {
        super(videoSurveillancePresenter);
    }

    @Override
    public VideoSurveillanceContract.Model getContract() {
        return new VideoSurveillanceContract.Model() {
        };
    }
}
