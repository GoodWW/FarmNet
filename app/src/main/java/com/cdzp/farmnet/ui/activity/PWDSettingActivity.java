package com.cdzp.farmnet.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.contract.pwdsetting.PWDSettingContract;
import com.cdzp.farmnet.contract.pwdsetting.PWDSettingPresenter;
import com.cdzp.farmnet.utils.Date;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.InjectView;
import com.cmonbaby.ioc.core.annotation.OnClick;

/**
 * 作者：张人文
 * 日期：2019/11/19 9:12
 * 邮箱：479696877@QQ.COM
 * 描述：首次登录设置密码
 */
@ContentView(R.layout.activity_pwdsetting)
public class PWDSettingActivity extends BaseView<PWDSettingPresenter, PWDSettingContract.View> {
    @InjectView(R.id.etPass)
    private EditText etPass;
    @InjectView(R.id.imgEye)
    private ImageView imgEye;
    private boolean flag = false;

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public PWDSettingContract.View getContract() {
        return new PWDSettingContract.View() {
        };
    }

    @Override
    public PWDSettingPresenter getPresenter() {
        return new PWDSettingPresenter();
    }

    @OnClick({/*R.id.username, R.id.password,  R.id.tvTime,*/ R.id.btnRegister, R.id.back, R.id.imgEye})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.imgEye:
                break;
            case R.id.btnRegister:
                p.getContract().requestSettingPWD(Date.userInfo.getPhone(),etPass.getText().toString(),Date.userInfo.getAuthCode());
                break;
            case R.id.back:
                finish();
                break;

        }

    }
}
