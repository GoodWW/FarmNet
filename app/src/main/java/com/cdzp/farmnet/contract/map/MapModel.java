package com.cdzp.farmnet.contract.map;

import com.cdzp.farmnet.base.BaseViewModel;

/**
 * 作者：张人文
 * 时间：2019/11/18 14:44
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class MapModel extends BaseViewModel<MapPresenter,MapContract.Model> {
    public MapModel(MapPresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    public MapContract.Model getContract() {
        return new MapContract.Model() {
            @Override
            public void excuteBack(String name, String code) throws Exception {

            }
        };
    }
}
