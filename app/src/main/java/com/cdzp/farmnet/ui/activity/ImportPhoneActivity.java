package com.cdzp.farmnet.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cdzp.farmnet.R;
import com.zhy.autolayout.AutoLayoutActivity;

import es.dmoral.toasty.Toasty;

/**
* 作者：张人文
* 日期：2020/2/25 11:48
* 邮箱：479696877@QQ.COM
* 描述：输入验证手机号码
*/
public class ImportPhoneActivity extends AutoLayoutActivity {
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_phone);
        etPhone = findViewById(R.id.etPhone);
    }
    public void back(View view) {
        finish();
    }

    public void importIntent(View view) {
        if ("".equals(etPhone.getText().toString())) {
            Toasty.error(ImportPhoneActivity.this, getResources().getString(R.string.str_long02), Toast.LENGTH_SHORT, true).show();
        }
    }
}
