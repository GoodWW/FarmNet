package com.cdzp.farmnet.contract.pwdsetting;

import android.annotation.SuppressLint;
import android.util.Log;

import com.cdzp.farmnet.api.IPWDSettingNetwork;
import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.ui.activity.PWDSettingActivity;
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
public class PWDSettingPresenter extends BaseViewPresenter<PWDSettingActivity, PWDSettingModel, PWDSettingContract.Presenter> {

    @Override
    public PWDSettingContract.Presenter getContract() {
        return new PWDSettingContract.Presenter() {
            @SuppressLint("CheckResult")
            @Override
            public void requestSettingPWD(String phone, String password, String authCode) {
                MyRetrofit.createRetrofit().create(IPWDSettingNetwork.class) //
                        .requestPWDSetting(authCode, password, phone)  // Observable<RegisterResponse> 上游 被观察者 耗时操作
                        .subscribeOn(Schedulers.io()) // todo 给上游分配异步线程
                        .observeOn(AndroidSchedulers.mainThread()) // todo 给下游切换 主线程
                        // 2.注册完成之后，更新注册UI
                        .subscribe(new Consumer<BaseEntity<UserInfo>>() {
                            @Override
                            public void accept(BaseEntity<UserInfo> userInfoBaseEntity) throws Exception {
                                Log.e("", "accept: " + userInfoBaseEntity.toString());
                                if (userInfoBaseEntity.isSuccess()) {
                                    responseSettingPWD(userInfoBaseEntity.getCode());
                                } else {
                                    responseSettingPWD(userInfoBaseEntity.getCode());
                                }
                            }
                        });
            }

            @Override
            public void responseSettingPWD(int code) {
                getView().getContract().handlerSettingPWDResult(code);

            }
        };
    }

    @Override
    public PWDSettingModel getModel() {
        return new PWDSettingModel(this);
    }
}
