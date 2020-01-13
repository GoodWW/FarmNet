package com.cdzp.farmnet.contract.home;

import com.cdzp.farmnet.base.BaseFragmentPresenter;
import com.cdzp.farmnet.ui.fragment.HomeFragment;

/**
 * 作者：张人文
 * 时间：2019/10/25 14:21
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class HomePresenter extends BaseFragmentPresenter<HomeFragment, HomeModel, HomeContract.Presenter> {
//    @Override
//    public HomeContract.Presenter getContract() {
        //纪要履行View给他的需求，又要分配工作给Model去完成这个需求
//        return new HomeContract.Presenter {
//            @Override
//            public void requestLogin(String name, String pwd) {
//                //详细讲三种方式(P层很极端，要么不做事，只做转发；要么就是拼命抓，一个人干活)
//                try {
        //，让model去完成
//                    m.getContract().executeLogin(name, pwd);
        //第二种，让功能模块去工作（library 下载 请求 图片加载）
//                    HttpEngine httpEngine = new HttpEngine<>(HomePresenter.this);
//                    httpEngine.post(name, pwd);
        //第三种，P层自己处理 不做转发  （谷歌官方的例子）
//                    if ("admin".equalsIgnoreCase(name) && "123".equalsIgnoreCase(pwd)) {
//                        responseResult(new UserInfo("智棚", "张人文"));
//                    } else {
//                        responseResult(null);
//                    }

        //内存泄漏测试
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            SystemClock.sleep(50000);
//                        }
////                    }).start();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

//            }
//
//            @Override
//            public void responseResult(UserInfo userInfo) {
//                //不管谁完成的，一旦有结果就告诉View层
//                getView().getContract().handlerResult(userInfo);
//            }
//        };
//    }

    @Override
    public HomeContract.Presenter getContract() {
        return new HomeContract.Presenter() {
        };
    }

    @Override
    public HomeModel getModel() {
        return new HomeModel(this);
    }

}
