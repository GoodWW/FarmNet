package com.cdzp.farmnet.contract.login;

import android.annotation.SuppressLint;

import com.cdzp.farmnet.api.ILoginRequestNetwork;
import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
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
public class LoginPresenter extends BaseViewPresenter<LoginActivity, LoginModel, LoginContract.Presenter> {
    @Override
    public LoginContract.Presenter getContract() {
        return new LoginContract.Presenter() {
            @SuppressLint("CheckResult")
            @Override
            public void requestLoginOrRegister(String name, String code, final int flag) {
                MyRetrofit.createRetrofit().create(ILoginRequestNetwork.class)
                        .requestLoginOrRegister(name, code)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<BaseEntity<UserInfo>>() {
                            @Override
                            public void accept(BaseEntity<UserInfo> userInfoBaseEntity) throws Exception {
                                userInfoBaseEntity.getData().getAuthCode();
                                responseLoginOrRegister(userInfoBaseEntity.getData(), flag);
                            }
                        });
            }

            @Override
            public void responseLoginOrRegister(UserInfo userInfo, int flag) {
                getView().getContract().handlerLoginOrRegisterResult(userInfo, flag);
            }

            @SuppressLint("CheckResult")
            @Override
            public void requestIsPhone(String strIsPhone) {
                MyRetrofit.createRetrofit().create(ILoginRequestNetwork.class) //
                        .isPhoneAction(strIsPhone)  // Observable<RegisterResponse> 上游 被观察者 耗时操作
                        .subscribeOn(Schedulers.io()) // todo 给上游分配异步线程
                        .observeOn(AndroidSchedulers.mainThread()) // todo 给下游切换 主线程
                        // 2.注册完成之后，更新注册UI
                        .subscribe(new Consumer<BaseEntity<UserInfo>>() {
                            @Override
                            public void accept(BaseEntity<UserInfo> userInfoBaseEntity) throws Exception {
                                if (null == userInfoBaseEntity.getData())
                                    responseIsPhone(false);
                                else
                                    responseIsPhone(true);
                            }
                        });
            }

            @Override
            public void responseIsPhone(boolean userInfo) {
                getView().getContract().handlerIsPhoneResult(userInfo);
            }
        };
    }

    @Override
    public LoginModel getModel() {
        return new LoginModel(this);
    }
}
