package com.cdzp.farmnet.ui.activity;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.contract.pwdsetting.PWDSettingContract;
import com.cdzp.farmnet.contract.pwdsetting.PWDSettingPresenter;
import com.cdzp.farmnet.utils.Date;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.InjectView;
import com.cmonbaby.ioc.core.annotation.OnClick;

import es.dmoral.toasty.Toasty;

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
            @Override
            public void handlerSettingPWDResult(int code) {
                switch (code) {
                    case 200:
                        startActivity(HomeActivity.class);
                        break;
                    case 1015:
                        Toasty.error(PWDSettingActivity.this, getString(R.string.str_long07), Toast.LENGTH_SHORT, true).show();
                        break;
                }
            }
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
                if (flag) {
                    imgEye.setImageResource(R.mipmap.ic_eye_open);
                    etPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = false;
                } else {
                    imgEye.setImageResource(R.mipmap.ic_eye_close);
                    etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = true;
                }
                break;
            case R.id.btnRegister:
                if (!"".equals(etPass.getText().toString()))
                    p.getContract().requestSettingPWD(Date.userInfo.getPhone(), etPass.getText().toString(), Date.userInfo.getAuthCode());
                else
                    etPass.setError(getString(R.string.str_pwd));
                break;
            case R.id.back:
                finish();
                break;

        }

    }
}
