package com.cdzp.farmnet.contract.map;

import com.cdzp.farmnet.base.BaseViewPresenter;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.ui.activity.LoginActivity;
import com.cdzp.farmnet.ui.activity.MapActivity;

/**
 * 作者：张人文
 * 时间：2019/11/18 14:44
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class MapPresenter extends BaseViewPresenter<MapActivity,MapModel,MapContract.Presenter> {
    @Override
    public MapContract.Presenter getContract() {
        return new MapContract.Presenter() {

            @Override
            public void requestLogin(String name, String code) {

            }

            @Override
            public void responseBackResult(BaseEntity baseEntity) {

            }
        };
    }

    @Override
    public MapModel getModel() {
        return new MapModel(this);
    }
}
