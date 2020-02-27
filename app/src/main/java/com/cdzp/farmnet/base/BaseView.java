package com.cdzp.farmnet.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cdzp.farmnet.utils.Date;
import com.cdzp.farmnet.utils.StatusBarUtil;
import com.cdzp.farmnet.utils.UUtils;
import com.cmonbaby.ioc.IOCManager;
import com.zhy.autolayout.AutoLayoutActivity;


/**
 * 作者：张人文
 * 时间：2019/10/25 13:40
 * 邮箱：479696877@QQ.COM
 * 描述：View的基类
 */
public abstract class BaseView<P extends BaseViewPresenter, CONTRACT> extends AutoLayoutActivity {
    private static final String TAG = "总标记";
    protected P p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //IoC注入
        IOCManager.initViewInject().injectView(this);

        //这里注意下 因为在评论区发现有网友调用setRootViewFitsSystemWindows 里面 winContent.getChildCount()=0 导致代码无法继续
        //是因为你需要在setContentView之后才可以调用 setRootViewFitsSystemWindows
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this,0x55000000);
        }

        //弱引用（很重要，解决内存泄漏）
        p = getPresenter();
        //绑定
        p.bindView(this);
        Date.height = UUtils.getStatusBarHeight(this);
        Log.e(TAG, "onCreate:    " + Date.height);
    }

    //同样VIew层也需要完成一个契约，让 P 层去做事情
    //只有在子类里面我才知道 做什么事情
    public abstract CONTRACT getContract();

    //同理 只有子类才知道是什么Presenter
    public abstract P getPresenter();

    //如果 Presenter 层出现了异常，就需要告知 View 层
    public void error(Exception e) {
    }

    public void startActivity(Class c) {
        Intent intent = new Intent();
        intent.setClass(this, c);
        startActivity(intent);
    }

    public void startActivityForResult(Class c, int code) {
        Intent intent = new Intent();
        intent.setClass(this, c);
        startActivityForResult(intent, code);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        p.unBindView();
    }
}
