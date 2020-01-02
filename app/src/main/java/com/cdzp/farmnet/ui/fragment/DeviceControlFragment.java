package com.cdzp.farmnet.ui.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseAdapter;
import com.cdzp.farmnet.base.BaseFragment;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.contract.devicecontrol.DeviceControlContract;
import com.cdzp.farmnet.contract.devicecontrol.DeviceControlPresenter;
import com.cdzp.farmnet.ui.activity.ControlActivity;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：张人文
 * 时间：2019/12/25 10:03
 * 邮箱：479696877@QQ.COM
 * 描述：设备控制Fragment
 */
public class DeviceControlFragment extends BaseFragment<DeviceControlPresenter, DeviceControlContract.View> {
    private SwipeRecyclerView mRecyclerView;
    private DeviceControlRecyclerAdapter mAdapter;
    private List<String> mDataList;

    @Override
    protected void initView(View mView) {
        mRecyclerView = mView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new DeviceControlRecyclerAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(ContextCompat.getColor(getActivity(), R.color.gray_line), 1, 1));
        mDataList = createDataList(0);
        mAdapter.notifyDataSetChanged(mDataList);
    }

    protected List<String> createDataList(int start) {
        List<String> strings = new ArrayList<>();
        for (int i = start; i < start + 8; i++) {
            strings.add("第" + i + "个Item");
        }
        return strings;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.share_recycler_view;
    }

    @Override
    public DeviceControlContract.View getContract() {
        return new DeviceControlContract.View() {
            @Override
            public void handlerDeviceListResult(BaseEntity baseEntity) {

            }
        };
    }

    @Override
    public DeviceControlPresenter getPresenter() {
        return new DeviceControlPresenter();
    }

    /**
     * 列表Adapter
     */
    private class DeviceControlRecyclerAdapter extends BaseAdapter<ViewHolder, String> {

        DeviceControlRecyclerAdapter(Context context) {
            super(context);
        }

        @Override
        public void notifyDataSetChanged(List<String> dataList) {
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_control, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img, imgSwitch;
        TextView tvControl, tvStop, tvOpen, tvClose;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvStop = itemView.findViewById(R.id.tvStop);
            tvOpen = itemView.findViewById(R.id.tvOpen);
            tvClose = itemView.findViewById(R.id.tvClose);
            imgSwitch = itemView.findViewById(R.id.imgSwitch);
            tvControl = itemView.findViewById(R.id.tvControl);
            img.setOnClickListener(this);
            tvControl.setOnClickListener(this);
            tvStop.setOnClickListener(this);
            tvOpen.setOnClickListener(this);
            tvClose.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img:
                case R.id.tvControl:
                    startActivity(ControlActivity.class);
                    break;
                case R.id.tvStop:
                    imgSwitch.setImageResource(R.drawable.ic_switch_stop);
                    break;
                case R.id.tvOpen:
                    imgSwitch.setImageResource(R.drawable.ic_switch_open);
                    break;
                case R.id.tvClose:
                    imgSwitch.setImageResource(R.drawable.ic_switch_close);
                    break;

            }
        }
    }
}
