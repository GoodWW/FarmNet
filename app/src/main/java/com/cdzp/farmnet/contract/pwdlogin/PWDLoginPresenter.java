package com.cdzp.farmnet.contract.pwdlogin;

import com.cdzp.farmnet.api.IPWDLoginRequestNetwork;
import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.ui.activity.PWDLoginActivity;
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
public class PWDLoginPresenter extends BaseViewPresenter<PWDLoginActivity, PWDModel, PWDLoginContract.Presenter> {
    @Override
    public PWDLoginContract.Presenter getContract() {
        return new PWDLoginContract.Presenter() {
            @Override
            public void requestLogin(String name, String pwd) {
                MyRetrofit.createRetrofit().create(IPWDLoginRequestNetwork.class)
                        .requestPWDLogin(name, pwd)
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
                                        responseResult(userInfoBaseEntity.getData(),userInfoBaseEntity.getCode());
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        responseResult(null,0);
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
            public void responseResult(UserInfo userInfo,int code) {
                getView().getContract().handlerResult(userInfo,code);
            }
        };
    }

    @Override
    public PWDModel getModel() {
        return new PWDModel(this);
    }
}
