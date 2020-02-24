package com.cdzp.farmnet.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.cdzp.farmnet.R;
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
        setStatusBar();
        //IoC注入
        IOCManager.initViewInject().injectView(this);
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
    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isUseFullScreenMode()) {
                StatusBarUtil.transparencyBar(this);
            } else {
                StatusBarUtil.setStatusBarColor(this, getStatusBarColor());
            }

            if (isUseBlackFontWithStatusBar()) {
                StatusBarUtil.setLightStatusBar(this, true, isUseFullScreenMode());
            }
        }
    }
    /**
     * 是否设置成透明状态栏，即就是全屏模式
     */
    protected boolean isUseFullScreenMode() {
        return false;
    }

    /**
     * 更改状态栏颜色，只有非全屏模式下有效
     */
    protected int getStatusBarColor() {
        return R.color.white;
    }

    /**
     * 是否改变状态栏文字颜色为黑色，默认为黑色
     */
    protected boolean isUseBlackFontWithStatusBar() {
        return true;
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
