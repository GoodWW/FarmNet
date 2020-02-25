package com.cdzp.farmnet.contract.importphone;

import com.cdzp.farmnet.api.IPWDLoginRequestNetwork;
import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.contract.pwdlogin.PWDLoginContract;
import com.cdzp.farmnet.contract.pwdlogin.PWDModel;
import com.cdzp.farmnet.ui.activity.ImportPhoneActivity;
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
public class ImportPhonePresenter extends BaseViewPresenter<ImportPhoneActivity, ImportPhoneModel, ImportPhoneContract.Presenter> {

    @Override
    public ImportPhoneContract.Presenter getContract() {
        return new ImportPhoneContract.Presenter() {
        };
    }

    @Override
    public ImportPhoneModel getModel() {
        return new ImportPhoneModel(this);
    }
}
