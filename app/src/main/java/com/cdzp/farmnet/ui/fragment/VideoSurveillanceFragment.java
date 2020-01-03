package com.cdzp.farmnet.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdzp.farmnet.R;

/**
 * 作者：张人文
 * 时间：2019/12/31 09:10
 * 邮箱：479696877@QQ.COM
 * 描述：视频监控Fragment
 */
public class VideoSurveillanceFragment extends Fragment {
    View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_video_surveillance, container, false);
        return mView;
    }
}
