package com.cdzp.farmnet.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.adapter.MyPagerAdapter;
import com.cdzp.farmnet.ui.fragment.DeviceControlFragment;
import com.cdzp.farmnet.ui.fragment.MyFragment;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.ArrayList;
import java.util.List;

/**
* 作者：张人文
* 日期：2019/12/26 10:45
* 邮箱：479696877@QQ.COM
* 描述：环境控制主界面
*/
public class EnvironmentControlActivity extends AppCompatActivity {

    QMUITabSegment mTabSegment;
    ViewPager mContentViewPager;
    QMUITopBarLayout mTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment_control);
        mTopBar = findViewById(R.id.topbar);
        mTabSegment = findViewById(R.id.tabSegment);
        mContentViewPager = findViewById(R.id.contentViewPager);
        initTopBar();
        initTabAndPager();
    }

    private void initTopBar() {
        mTopBar.addLeftImageButton(R.drawable.ic_chevron_left_black_24dp, R.id.back);
        mTopBar.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTopBar.setTitle("环境控制");
//        mTopBar.addRightImageButton(R.mipmap.icon_topbar_overflow, R.id.topbar_right_change_button)
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        showBottomSheetList();
//                    }
//                });
    }

    private void initTabAndPager() {
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new DeviceControlFragment());
        mFragments.add(new MyFragment());
        mFragments.add(new DeviceControlFragment());
        mFragments.add(new MyFragment());
        mFragments.add(new DeviceControlFragment());
        String[] mTitles = new String[]{
                "设备控制",
                "参数设置",
                "视频监控",
                "传感数据",
                "报警提示"
        };
        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mContentViewPager.setAdapter(mAdapter);
        mContentViewPager.setCurrentItem(mFragments.get(0).getId(), false);

        mTabSegment.reset();
        mTabSegment.setHasIndicator(true);
        mTabSegment.setIndicatorPosition(false);
        mTabSegment.setIndicatorWidthAdjustContent(true);
        for (String mTitle : mTitles) {
            mTabSegment.addTab(new QMUITabSegment.Tab(mTitle));
        }
        mTabSegment.setupWithViewPager(mContentViewPager, false);
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
        mTabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                mTabSegment.hideSignCountView(index);
            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {
                mTabSegment.hideSignCountView(index);
            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
    }


}
