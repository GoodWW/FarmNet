package com.cdzp.farmnet.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseAdapter;
import com.cdzp.farmnet.base.BaseFragment;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.contract.home.HomeContract;
import com.cdzp.farmnet.contract.home.HomePresenter;
import com.cdzp.farmnet.contract.home.HomeRecyclerAdapter;
import com.cdzp.farmnet.ui.activity.AddFrameActivity;
import com.cdzp.farmnet.ui.activity.EnvironmentControlActivity;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.touch.OnItemMoveListener;
import com.yanzhenjie.recyclerview.touch.OnItemStateChangedListener;
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者：张人文
 * <p>
 * 时间：2019/11/4 11:26
 * 邮箱：479696877@QQ.COM
 * 描述：主页
 */
public class HomeFragment extends BaseFragment<HomePresenter, HomeContract.View> implements View.OnClickListener, OnItemClickListener {

    private SwipeRefreshLayout mRefreshLayout;
    private SwipeRecyclerView mRecyclerView;
    private HomeRecyclerAdapter mAdapter;
    private List<String> mDataList;
    private ImageView imgName, imgAdd;
    private TextView tvName;
    private static final int VIEWTYPE_TWO = 1;
    private PopupWindow popupWindow;
    SwipeRecyclerView rl;


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgName:
            case R.id.tvName:
                popupWindow.showAsDropDown(imgName);
                break;
            case R.id.imgAdd:
                startActivity(AddFrameActivity.class);
                break;
        }
    }

    @Override
    public void onItemClick(View itemView, int position) {
        Toast.makeText(getActivity(), "第" + position + "个", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initView(View mView) {
        mRefreshLayout = mView.findViewById(R.id.refresh_layout);
        @SuppressLint("InflateParams")
        View popupView = getLayoutInflater().inflate(R.layout.pop_layout, null);

        rl = popupView.findViewById(R.id.recycler_view);
        rl.setOnItemClickListener(this);
        rl.setLayoutManager(new LinearLayoutManager(getActivity()));
        rl.setAdapter(new DefineAdapter(getActivity()));
        rl.addItemDecoration(new DefaultItemDecoration(ContextCompat.getColor(getActivity(), R.color.white), 1, 1));


        popupWindow = new PopupWindow(popupView, 400, 28 + 500 + 10, true);


        tvName = mView.findViewById(R.id.tvName);
        imgName = mView.findViewById(R.id.imgName);
        imgAdd = mView.findViewById(R.id.imgAdd);
        imgName.setOnClickListener(this);
        tvName.setOnClickListener(this);
        imgAdd.setOnClickListener(this);

        mRefreshLayout.setOnRefreshListener(mRefreshListener); // 刷新监听。
        mRecyclerView = mView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        if (null == mAdapter) {
            mRecyclerView.setOnItemClickListener(mItemClickListener); // RecyclerView Item点击监听。
        }
        mRecyclerView.useDefaultLoadMore(); // 使用默认的加载更多的View。
        mRecyclerView.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。

        mAdapter = new HomeRecyclerAdapter(getActivity()) {
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
        mAdapter.notifyDataSetChanged(mDataList);
        mRecyclerView.setOnItemMoveListener(getItemMoveListener());// 监听拖拽和侧滑删除，更新UI和数据源。
        mRecyclerView.setOnItemStateChangedListener(mOnItemStateChangedListener); // 监听Item的手指状态，拖拽、侧滑、松开。
        mRecyclerView.setLongPressDragEnabled(true); // 长按拖拽，默认关闭。
        mRecyclerView.setItemViewSwipeEnabled(true); // 滑动删除，默认关闭。

        // 请求服务器加载数据。
        loadData();
    }


    @Override
    public HomeContract.View getContract() {
        return new HomeContract.View<UserInfo>() {
            @Override
            public void handlerResult(UserInfo userInfo) {

            }
        };
    }

    @Override
    public HomePresenter getPresenter() {
        return new HomePresenter();
    }

    /**
     * 刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadData();
                }
            }, 1000); // 延时模拟请求服务器。
        }
    };

    /**
     * 加载更多。
     */
    private SwipeRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            mRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    List<String> strings = createDataList(mAdapter.getItemCount());
                    mDataList.addAll(strings);
                    // notifyItemRangeInserted()或者notifyDataSetChanged().
                    mAdapter.notifyItemRangeInserted(mDataList.size() - strings.size(), strings.size());

                    // 数据完更多数据，一定要掉用这个方法。
                    // 第一个参数：表示此次数据是否为空。
                    // 第二个参数：表示是否还有更多数据。
                    mRecyclerView.loadMoreFinish(false, true);

                    // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
                    // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
                    // errorMessage是会显示到loadMoreView上的，用户可以看到。
                    // mRecyclerView.loadMoreError(0, "请求网络失败");
                }
            }, 1000);
        }
    };

    /**
     * Item点击监听。
     */
    private OnItemClickListener mItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View itemView, int position) {
            if (position == (mAdapter.getItemCount() - 1)) {
                Toast.makeText(getActivity(), "添加按钮", Toast.LENGTH_SHORT).show();
            } else
                startActivity(EnvironmentControlActivity.class);
//                showSimpleBottomSheetList();
        }
    };

    private void showSimpleBottomSheetList() {
        new QMUIBottomSheet.BottomListSheetBuilder(getActivity())
                .addItem("环境控制")
                .addItem("水肥控制")
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        startActivity(EnvironmentControlActivity.class);
                    }
                })
                .build()
                .show();
    }

    /**
     * 第一次加载数据。
     */
    private void loadData() {
        mDataList = createDataList(0);
        mAdapter.notifyDataSetChanged(mDataList);

        mRefreshLayout.setRefreshing(false);

        // 第一次加载数据：一定要调用这个方法，否则不会触发加载更多。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        mRecyclerView.loadMoreFinish(false, true);
    }

    protected List<String> createDataList(int start) {
        List<String> strings = new ArrayList<>();
        for (int i = start; i < start + 8; i++) {
            strings.add("第" + i + "个Item");
        }
        return strings;
    }

    /**
     * Item的拖拽/侧滑删除时，手指状态发生变化监听。
     */
    private OnItemStateChangedListener mOnItemStateChangedListener = new OnItemStateChangedListener() {
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState == OnItemStateChangedListener.ACTION_STATE_DRAG) {
//                mActionBar.setSubtitle("状态：拖拽");

                // 拖拽的时候背景就透明了，这里我们可以添加一个特殊背景。
                viewHolder.itemView.setBackgroundColor(
                        ContextCompat.getColor(getActivity(), R.color.white_pressed));
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibrator.vibrate(100);
            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_SWIPE) {
//                mActionBar.setSubtitle("状态：滑动删除");
            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_IDLE) {
//                mActionBar.setSubtitle("状态：手指松开");

                // 在手松开的时候还原背景。
                ViewCompat.setBackground(viewHolder.itemView,
                        ContextCompat.getDrawable(getActivity(), R.drawable.select_white));
            }
        }
    };

    protected OnItemMoveListener getItemMoveListener() {
        // 监听拖拽和侧滑删除，更新UI和数据源。
        return new OnItemMoveListener() {
            @Override
            public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
                // 不同的ViewType不能拖拽换位置。
                if (srcHolder.getItemViewType() != targetHolder.getItemViewType()) return false;

                // 真实的Position：通过ViewHolder拿到的position都需要减掉HeadView的数量。
                int fromPosition = srcHolder.getAdapterPosition() - mRecyclerView.getHeaderCount();
                int toPosition = targetHolder.getAdapterPosition() - mRecyclerView.getHeaderCount();

                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++)
                        Collections.swap(mDataList, i, i + 1);
                } else {
                    for (int i = fromPosition; i > toPosition; i--)
                        Collections.swap(mDataList, i, i - 1);
                }

                mAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;// 返回true表示处理了，返回false表示你没有处理。
            }

            @Override
            public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
                int adapterPosition = srcHolder.getAdapterPosition();
                final int position = adapterPosition - mRecyclerView.getHeaderCount();

                if (mRecyclerView.getHeaderCount() > 0 && adapterPosition == 0) { // HeaderView。
//                    mRecyclerView.removeHeaderView(mHeaderView);
                    Toast.makeText(getActivity(), "HeaderView被删除。", Toast.LENGTH_SHORT).show();
                } else if (position == (mDataList.size() - 1)) {
                    Toast.makeText(getActivity(), "不能删除加号。", Toast.LENGTH_SHORT).show();
                    mAdapter.notifyItemRemoved(position);
                } else if (position < (mDataList.size() - 1)) {// 普通Item。

                    new QMUIDialog.MessageDialogBuilder(getContext())
                            .setTitle("提示")
                            .setMessage("是否删除")
                            .addAction("取消", new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    mAdapter.notifyItemChanged(position);
                                    dialog.dismiss();
                                }
                            })
                            .addAction("确认", new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    mDataList.remove(position);
                                    mAdapter.notifyItemRemoved(position);
                                    Toast.makeText(getActivity(), "现在的第" + position + "条被删除。", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            })
//                            .setCancelable(false)
                            .setCanceledOnTouchOutside(false)
                            .create().show();
                }
            }
        };
    }


    /**
     * 就是这个适配器的Item的Layout需要处理，其实和CardView的方式一模一样。
     */
    private static class DefineAdapter extends BaseAdapter<ViewHolder, String> {

        DefineAdapter(Context context) {
            super(context);
        }

        @Override
        public void notifyDataSetChanged(List<String> dataList) {
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_define, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Button btnItemJump;

        ViewHolder(View itemView) {
            super(itemView);
            btnItemJump = itemView.findViewById(R.id.btnItemJump);
            btnItemJump.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnItemJump: {
                    Toast.makeText(v.getContext(), "我是第" + getAdapterPosition() + "个Item的中间的Button", Toast.LENGTH_SHORT)
                            .show();
                    break;
                }

            }
        }
    }
}
