package com.cdzp.farmnet.contract.setpwd;

import android.util.Log;

import com.cdzp.farmnet.api.IPWDSettingNetwork;
import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.ui.activity.SettingPWDActivity;
import com.cdzp.farmnet.utils.MyRetrofit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：张人文
 * 时间：2019/11/18 17:42
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class SetPWDPresenter extends BaseViewPresenter<SettingPWDActivity, SetPWDModel, SetPWDContract.Presenter> {
    private static final String TAG = "重置密码";

    @Override
    public SetPWDContract.Presenter getContract() {
        return new SetPWDContract.Presenter() {
            @Override
            public void requestSetPWD(String authCode, String password, String phone) {
                MyRetrofit.createRetrofit().create(IPWDSettingNetwork.class)
                        .requestPWDSetting(authCode, password, phone)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Observer<BaseEntity<UserInfo>>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                    }

                                    @Override
                                    public void onNext(BaseEntity<UserInfo> userInfoBaseEntity) {
                                        responseResult(userInfoBaseEntity.getData(), userInfoBaseEntity.getCode());
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        responseResult(null, 0);
                                        Log.e(TAG, "requestLoginOrRegister onError:     " + e.getMessage());
                                    }

                                    @Override
                                    public void onComplete() {
                                    }
                                }
                        );
            }

            @Override
            public void responseResult(UserInfo userInfo, int code) {
                getView().getContract().handlerResult(userInfo, code);
            }
        };
    }

    @Override
    public SetPWDModel getModel() {
        return new SetPWDModel(this);
    }
}
