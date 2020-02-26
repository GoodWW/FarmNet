package com.cdzp.farmnet.contract.register;

import com.cdzp.farmnet.api.IRegisterNetwork;
import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.ui.activity.RegisterActivity;
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
public class RegisterPresenter extends BaseViewPresenter<RegisterActivity,RegisterModel,RegisterContract.Presenter> {

    @Override
    public RegisterContract.Presenter getContract() {
        return new RegisterContract.Presenter() {
            @Override
            public void requestRegister(String account, String pwd) {
                MyRetrofit.createRetrofit().create(IRegisterNetwork.class)
                        .requestRegister(account, pwd)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Observer<BaseEntity<UserInfo>>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                    }

                                    @Override
                                    public void onNext(BaseEntity<UserInfo> userInfoBaseEntity) {
//                                        Log.e(TAG, "onNext: " + flag);
                                        responseRegisterResult(userInfoBaseEntity.getData(),userInfoBaseEntity.getCode());
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        responseRegisterResult(null,0);
//                                        Log.e(TAG, "requestLoginOrRegister onError:     " + e.getMessage());
//                                        responseLoginOrRegister(null, 0, 0);
                                    }

                                    @Override
                                    public void onComplete() {
                                    }
                                }
                        );
            }

            @Override
            public void responseRegisterResult(UserInfo t,int code) {
                getView().getContract().handlerRegisterResult(t,code);

            }
        };
    }

    @Override
    public RegisterModel getModel() {
        return new RegisterModel(this);
    }
}
