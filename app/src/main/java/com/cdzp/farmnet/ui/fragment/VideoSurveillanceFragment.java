package com.cdzp.farmnet.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseFragment;
import com.cdzp.farmnet.contract.video_surveillance.VideoListAdapter;
import com.cdzp.farmnet.contract.video_surveillance.VideoSurveillanceContract;
import com.cdzp.farmnet.contract.video_surveillance.VideoSurveillancePresenter;
import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：张人文
 * 时间：2019/12/31 09:10
 * 邮箱：479696877@QQ.COM
 * 描述：视频监控Fragment
 */
public class VideoSurveillanceFragment extends BaseFragment<VideoSurveillancePresenter, VideoSurveillanceContract.View> {
    View mView;
    private SwipeRecyclerView mRecyclerView;
    private VideoListAdapter mAdapter;
    private static final int VIEWTYPE_TWO = 1;
    private List<String> mDataList;


    @Override
    protected void initView(View mView) {
        mRecyclerView = mView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerView.setOnItemClickListener(mItemClickListener); // RecyclerView Item点击监听。
        mRecyclerView.useDefaultLoadMore(); // 使用默认的加载更多的View。

        mAdapter = new VideoListAdapter(getActivity()) {
            @Override
            public int getItemViewType(int position) {
                Log.e("", mAdapter.getItemCount() + "      getItemViewType:       " + position);

                if (position == (mAdapter.getItemCount() - 1)) {
                    return VIEWTYPE_TWO;
                } else
                    return super.getItemViewType(position);
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        mDataList = createDataList(0);
        mAdapter.notifyDataSetChanged(mDataList);

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_video_surveillance;
    }

    @Override
    public VideoSurveillanceContract.View getContract() {
        return new VideoSurveillanceContract.View() {
        };
    }

    @Override
    public VideoSurveillancePresenter getPresenter() {
        return new VideoSurveillancePresenter();
    }

    /**
     * Item点击监听。
     */
    private OnItemClickListener mItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View itemView, int position) {
            if (position == (mAdapter.getItemCount() - 1)) {
                Toast.makeText(getActivity(), "添加按钮", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getActivity(), "点击了第" + (position + 1) + "个条目", Toast.LENGTH_SHORT).show();
//                showSimpleBottomSheetList();
        }
    };

    protected List<String> createDataList(int start) {
        List<String> strings = new ArrayList<>();
        for (int i = start; i < start + 6; i++) {
            strings.add("第" + i + "个Item");
        }
        return strings;
    }
}
