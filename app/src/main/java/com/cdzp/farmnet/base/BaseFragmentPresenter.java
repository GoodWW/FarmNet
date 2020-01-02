package com.cdzp.farmnet.base;

import java.lang.ref.WeakReference;

/**
 * 作者：张人文
 * 时间：2019/10/25 11:55
 * 邮箱：479696877@QQ.COM
 * 描述： Presenter (基类)
 */
public abstract class BaseFragmentPresenter<V extends BaseFragment, M extends BaseFragmentModel, CONTRACT> {

    protected M m;
    //绑定View层的弱引用
    private WeakReference<V> vWeakReference;

    public BaseFragmentPresenter() {
        m = getModel();
    }

    public void bindView(V v) {
        vWeakReference = new WeakReference<>(v);
    }

    public void unBindView() {
        if (vWeakReference != null) {
            vWeakReference.clear();
            vWeakReference = null;
            System.gc();
        }
    }

    //从弱引用中去拿 View的对象  获取View  P -- V的一个过程
    public V getView() {
        if (vWeakReference != null) {
            return vWeakReference.get();
        }
        return null;
    }

    //还是同样需要一个契约
    //子类具体契约（Mode层和View层协商的共同业务）
    public abstract CONTRACT getContract();

    public abstract M getModel();
}
