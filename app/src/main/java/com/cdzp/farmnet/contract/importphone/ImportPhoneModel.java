package com.cdzp.farmnet.contract.importphone;

import com.cdzp.farmnet.base.BaseViewModel;

/**
 * 作者：张人文
 * 时间：2019/11/18 17:42
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class ImportPhoneModel extends BaseViewModel<ImportPhonePresenter, ImportPhoneContract.Model> {


    public ImportPhoneModel(ImportPhonePresenter importPhonePresenter) {
        super(importPhonePresenter);
    }

    @Override
    public ImportPhoneContract.Model getContract() {
        return new ImportPhoneContract.Model() {
        };
    }
}
