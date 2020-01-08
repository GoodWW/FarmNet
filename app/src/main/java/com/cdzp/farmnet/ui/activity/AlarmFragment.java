package com.cdzp.farmnet.ui.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseAdapter;
import com.cdzp.farmnet.base.BaseFragment;
import com.cdzp.farmnet.contract.alarm.AlarmContract;
import com.cdzp.farmnet.contract.alarm.AlarmPresenter;
import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：张人文
 * 时间：2020/1/7 15:55
 * 邮箱：479696877@QQ.COM
 * 描述：报警提示Fragment
 */
public class AlarmFragment extends BaseFragment<AlarmPresenter, AlarmContract.View> implements View.OnClickListener, OnItemClickListener {
    private TextView tvHistorical, tvCurrent;
    private SwipeRecyclerView recyclerView;
    private List<String> mDataList;
    private DefineAdapter adapter;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvHistorical:
                tvHistorical.setTextColor(mContext.getResources().getColor(R.color.white));
                tvCurrent.setTextColor(mContext.getResources().getColor(R.color.font_dark));
                tvHistorical.setBackground(mContext.getDrawable(R.drawable.bg_text_blue));
                tvCurrent.setBackground(mContext.getDrawable(R.drawable.bg_text_white));

                flag = 2;
                mDataList = createDataList(2);
                adapter.notifyDataSetChanged(mDataList);
                break;
            case R.id.tvCurrent:
                tvCurrent.setTextColor(mContext.getResources().getColor(R.color.white));
                tvHistorical.setTextColor(mContext.getResources().getColor(R.color.font_dark));
                tvHistorical.setBackground(mContext.getDrawable(R.drawable.bg_text_white));
                tvCurrent.setBackground(mContext.getDrawable(R.drawable.bg_text_blue));

                flag = 1;
                mDataList = createDataList(0);
                adapter.notifyDataSetChanged(mDataList);
                break;

        }

    }

    @Override
    protected void initView(View mView) {
        tvHistorical = mView.findViewById(R.id.tvHistorical);
        tvCurrent = mView.findViewById(R.id.tvCurrent);
        tvCurrent.setOnClickListener(this);
        tvHistorical.setOnClickListener(this);

        recyclerView = mView.findViewById(R.id.recycler_view);
//        recyclerView.setOnItemClickListener(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DefineAdapter(mContext);
        recyclerView.setAdapter(adapter);
        flag = 1;
        mDataList = createDataList(0);
        adapter.notifyDataSetChanged(mDataList);
//        rl.addItemDecoration(new DefaultItemDecoration(ContextCompat.getColor(getActivity(), R.color.white), 1, 1));

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


    @Override
    public void onItemClick(View view, int adapterPosition) {

    }

    /**
     * 就是这个适配器的Item的Layout需要处理，其实和CardView的方式一模一样。
     */
    private static class DefineAdapter extends BaseAdapter<ViewHolder, String> {
        private List<String> mDataList;

        DefineAdapter(Context context) {
            super(context);
        }

        public void notifyDataSetChanged(List<String> dataList) {
            this.mDataList = dataList;
            super.notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return mDataList == null ? 0 : mDataList.size();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.setData(mDataList.get(position));
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle;
        ImageView imgDown;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            imgDown = itemView.findViewById(R.id.imgDown);
            imgDown.setOnClickListener(this);
        }

        public void setData(String title) {
            this.tvTitle.setText(title);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imgDown:
                    Toast.makeText(v.getContext(), "我是第" + (getAdapterPosition() + 1) + "个Item的向下按钮", Toast.LENGTH_SHORT)
                            .show();
                    break;

            }
        }
    }

    private int flag = 1;

    protected List<String> createDataList(int start) {
        List<String> strings = new ArrayList<>();
        if (flag == 1) {
            for (int i = start; i < start + 5; i++) {
                strings.add("当前报警第" + i + "个Item");
            }
        } else {
            for (int i = start; i < start + 3; i++) {
                strings.add("历史报警第" + i + "个Item");
            }
        }

        return strings;
    }
}
