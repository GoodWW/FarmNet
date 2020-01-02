package com.cdzp.farmnet.base;

/**
 * 作者：张人文
 * 时间：2019/10/25 11:23
 * 邮箱：479696877@QQ.COM
 * 描述：   接受到 P 层交给他的需求
 *          定义成抽象类，子类去继承他，就必须重写他的抽象方法
 *          （基类）
 */
public abstract class BaseFragmentModel<P extends BaseFragmentPresenter,CONTRACT> {
    //需要接收到 P 层 需要做的事情
    protected P p;

    //业务结束 需要通过 Presenter 调用  锲约  （合同）（也就是接口中的方法  void responseResult(T t);）

    public BaseFragmentModel(P p) {
        this.p = p;
    }

    //传什么就给你返回什么，有利于重用
    public abstract CONTRACT getContract();
}
