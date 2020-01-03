package com.cdzp.farmnet.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：张人文
 * 时间：2019/11/4 11:05
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public abstract class BaseFragment<P extends BaseFragmentPresenter, CONTRACT> extends Fragment {
    protected P p;
    private View mView;
    public Context mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //弱引用（很重要，解决内存泄漏）
        p = getPresenter();
        //绑定
        p.bindView(this);
        if (mView == null)
            mView = inflater.inflate(getLayoutID(), container, false);
        mContext = getActivity();
        initView(mView);
        return mView;
    }

    public void startActivity(Class c) {
        Intent intent = new Intent(getActivity(), c);
        startActivity(intent);
    }

    protected abstract void initView(View mView);

    protected abstract int getLayoutID();

    //同样VIew层也需要完成一个契约，让 P 层去做事情
    //只有在子类里面我才知道 做什么事情
    public abstract CONTRACT getContract();

    //同理 只有子类才知道是什么Presenter
    public abstract P getPresenter();

    //如果 Presenter 层出现了异常，就需要告知 View 层
    public void error(Exception e) {
    }


    @Override
    public void onDetach() {
        super.onDetach();
        //解除绑定
        p.unBindView();
    }
}
