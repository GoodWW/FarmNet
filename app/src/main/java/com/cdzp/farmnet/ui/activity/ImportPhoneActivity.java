package com.cdzp.farmnet.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.contract.importphone.ImportPhoneContract;
import com.cdzp.farmnet.contract.importphone.ImportPhonePresenter;
import com.cdzp.farmnet.utils.RegexStringUtil;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.InjectView;
import com.cmonbaby.ioc.core.annotation.OnClick;

import es.dmoral.toasty.Toasty;

/**
 * 作者：张人文
 * 日期：2020/2/25 11:48
 * 邮箱：479696877@QQ.COM
 * 描述：输入验证手机号码
 */
@ContentView(R.layout.activity_import_phone)
public class ImportPhoneActivity extends BaseView<ImportPhonePresenter, ImportPhoneContract.View> {
    @InjectView(R.id.etPhone)
    private EditText etPhone;

    @Override
    public ImportPhoneContract.View getContract() {
        return new ImportPhoneContract.View() {
        };
    }

    @Override
    public ImportPhonePresenter getPresenter() {
        return new ImportPhonePresenter();
    }


    @OnClick({R.id.back, R.id.btnRegister})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                if (!"".equals(etPhone.getText().toString())) {
                    if (RegexStringUtil.MatchPhoneNum(etPhone.getText().toString())) {
                        Intent intent = new Intent(ImportPhoneActivity.this, AuthenticationActivity.class);
                        intent.putExtra("phone", etPhone.getText().toString());
                        startActivity(intent);
                    } else {
                        etPhone.setError(getString(R.string.str_long14));
                        Toasty.error(ImportPhoneActivity.this, getString(R.string.str_long14), Toast.LENGTH_SHORT, true).show();
                    }
                } else {
                    etPhone.setError(getResources().getString(R.string.str_long02));
                    Toasty.error(ImportPhoneActivity.this, getResources().getString(R.string.str_long02), Toast.LENGTH_SHORT, true).show();
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }

}
