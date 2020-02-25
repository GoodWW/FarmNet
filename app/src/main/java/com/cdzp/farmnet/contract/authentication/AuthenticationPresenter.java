package com.cdzp.farmnet.contract.authentication;

import com.cdzp.farmnet.api.IAuthenticationNetwork;
import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.ui.activity.AuthenticationActivity;
import com.cdzp.farmnet.utils.MyRetrofit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：张人文
 * 时间：2019/11/18 14:44
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class AuthenticationPresenter extends BaseViewPresenter<AuthenticationActivity, AuthenticationModel, AuthenticationContract.Presenter> {
    @Override
    public AuthenticationContract.Presenter getContract() {
        return new AuthenticationContract.Presenter() {
            @Override
            public void requestCode(String phone) {
                MyRetrofit.createRetrofit().create(IAuthenticationNetwork.class)
                        .requestCode(phone)
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
                                    }

                                    @Override
                                    public void onComplete() {
                                    }
                                }
                        );
            }

            @Override
            public void judgeCode(String code, String phone) {
                MyRetrofit.createRetrofit().create(IAuthenticationNetwork.class)
                        .judgeCode(code, phone)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Observer<BaseEntity<UserInfo>>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                    }

                                    @Override
                                    public void onNext(BaseEntity<UserInfo> userInfoBaseEntity) {
                                        responseJudgeCodeResult(userInfoBaseEntity.getData(), userInfoBaseEntity.getCode());
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        responseJudgeCodeResult(null, 0);
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

            @Override
            public void responseJudgeCodeResult(UserInfo userInfo, int code) {
                getView().getContract().handlerJudgeCodeResult(userInfo, code);
            }
        };
    }

    @Override
    public AuthenticationModel getModel() {
        return new AuthenticationModel(this);
    }
}
