package com.cdzp.farmnet.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.MenuItem;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.contract.homeactivity.HomeActivityContract;
import com.cdzp.farmnet.contract.homeactivity.HomeActivityPresenter;
import com.cdzp.farmnet.ui.fragment.HomeFragment;
import com.cdzp.farmnet.ui.fragment.MyFragment;
import com.cdzp.farmnet.utils.MyViewPager;
import com.cdzp.farmnet.utils.StatusBarUtil;
import com.cmonbaby.ioc.core.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：张人文
 * 日期：2019/11/19 9:11
 * 邮箱：479696877@QQ.COM
 * 描述：导航栏主页
 */
@ContentView(R.layout.activity_home)
public class HomeActivity extends BaseView<HomeActivityPresenter, HomeActivityContract.View> {
    /**
     * android 4.4以上沉浸式以及bar的管理
     */
//    private ImmersionBar mImmersionBar;
    //导航栏菜单点击监听
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_capacity:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_service:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_my:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };
    MyViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setRootViewFitsSystemWindows(this,false);
        //bar初始化
//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.init();

        //iewPager相关初始化，数据绑定

        viewPager = findViewById(R.id.frame);
        final List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());//
        fragmentList.add(new MyFragment());//
        fragmentList.add(new MyFragment());//
        fragmentList.add(new MyFragment());//
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


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setLabelVisibilityMode(1);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public HomeActivityContract.View getContract() {
        return new HomeActivityContract.View() {
        };
    }

    @Override
    public HomeActivityPresenter getPresenter() {
        return new HomeActivityPresenter();
    }


    //退出时注销bar 和关闭socket
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mImmersionBar != null) mImmersionBar.destroy();
    }

}
