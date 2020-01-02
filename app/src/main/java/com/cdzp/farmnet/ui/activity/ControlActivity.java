package com.cdzp.farmnet.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.ui.fragment.ManualOperationFragment;
import com.cdzp.farmnet.ui.fragment.SelfMotionFragment;
import com.cdzp.farmnet.ui.fragment.TimingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：张人文
 * 日期：2019/12/26 10:43
 * 邮箱：479696877@QQ.COM
 * 描述：控制方式（定时 手动 自动）
 */
public class ControlActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RelativeLayout rlCenter;
    private float mPosX;
    private float mPosY;
    private float mCurPosX;
    private float mCurPosY;
    LinearLayout.LayoutParams lp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        tabLayout = findViewById(R.id.toolbar_tab);
        viewPager = findViewById(R.id.main_vp_container);
        rlCenter = findViewById(R.id.rlCenter);
        lp = new LinearLayout.LayoutParams(rlCenter.getLayoutParams());
        rlCenter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        mPosX = event.getX();
                        mPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurPosX = event.getX();
                        mCurPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mCurPosY - mPosY > 0
                                && (Math.abs(mCurPosY - mPosY) > 25)) {
                            //向下滑動
                            lp.setMargins(0, 0, 0, 300);
                            rlCenter.setLayoutParams(lp);
                            Log.e("", "向下: " );

                        } else if (mCurPosY - mPosY < 0
                                && (Math.abs(mCurPosY - mPosY) > 25)) {
                            //向上滑动
                            lp.setMargins(0, 0, 0, 0);
                            rlCenter.setLayoutParams(lp);
                            Log.e("", "向上: " );
                        }

                        break;

                }
                return true;
            }
        });
        //相互绑定，这样可以防止图标被替换
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener
                (tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener
                (viewPager));

        final List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new TimingFragment());//
        fragmentList.add(new ManualOperationFragment());//
        fragmentList.add(new SelfMotionFragment());//
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        viewPager.setAdapter(pagerAdapter);//viewPager到这里结束
    }
}
