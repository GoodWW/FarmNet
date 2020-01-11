package com.cdzp.farmnet.contract.login;

import android.annotation.SuppressLint;
import android.util.Log;

import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.bean.TestBean;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.ui.activity.LoginActivity;
import com.cdzp.farmnet.utils.MyRetrofit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：张人文
 * 时间：2019/11/18 14:44
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class LoginPresenter extends BaseViewPresenter<LoginActivity,LoginModel,LoginContract.Presenter> {
    @Override
    public LoginContract.Presenter getContract() {
        return new LoginContract.Presenter<UserInfo>() {
            @Override
            public void requestLogin(String name, String code) {

            }

            @Override
            public void responseResult(UserInfo baseEntity) {

            }

            @SuppressLint("CheckResult")
            @Override
            public void requestIsPhone(String strIsPhone) {
                MyRetrofit.createRetrofit().create(IRequestNetwork.class) // IRequestNetwork
                        // IRequestNetwork.registerAction
                        .isPhoneAction(strIsPhone)  // Observable<RegisterResponse> 上游 被观察者 耗时操作
                        .subscribeOn(Schedulers.io()) // todo 给上游分配异步线程
                        .observeOn(AndroidSchedulers.mainThread()) // todo 给下游切换 主线程
                        // 2.注册完成之后，更新注册UI
                        .subscribe(new Consumer<BaseEntity<BaseEntity<TestBean>>>() {
                            @Override
                            public void accept(BaseEntity<BaseEntity<TestBean>> baseEntity) throws Exception {
                                Log.e("  ", "accept: "+ baseEntity.toString());
                            }
                        });

            }

            @Override
            public void responseIsPhone(UserInfo userInfo) {
//                getView().getContract().handlerIsPhoneResult(userInfo);
            }


        };
    }

    @Override
    public LoginModel getModel() {
        return new LoginModel(this);
    }
}
