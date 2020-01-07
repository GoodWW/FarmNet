package com.cdzp.farmnet.contract.alarm;

import com.cdzp.farmnet.base.BaseFragmentModel;

/**
 * 作者：张人文
 * 时间：2019/11/20 11:04
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class AlarmModel extends BaseFragmentModel<AlarmPresenter, AlarmContract.Model> {

    public AlarmModel(AlarmPresenter alarmPresenter) {
        super(alarmPresenter);
    }

    @Override
    public AlarmContract.Model getContract() {
        return new AlarmContract.Model() {
        };
    }
}
