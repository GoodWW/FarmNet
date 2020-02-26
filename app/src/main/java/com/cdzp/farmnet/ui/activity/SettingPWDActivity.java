package com.cdzp.farmnet.ui.activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.contract.setpwd.SetPWDContract;
import com.cdzp.farmnet.contract.setpwd.SetPWDPresenter;
import com.cdzp.farmnet.utils.Date;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.InjectView;
import com.cmonbaby.ioc.core.annotation.OnClick;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import es.dmoral.toasty.Toasty;

/**
 * 作者：张人文
 * 日期：2019/11/19 10:19
 * 邮箱：479696877@QQ.COM
 * 描述：忘记密码下设置密码
 */
@ContentView(R.layout.activity_setting_pwd)
public class SettingPWDActivity extends BaseView<SetPWDPresenter, SetPWDContract.View> {
    private static final String TAG = "重置密码";
    @InjectView(R.id.imgEye)
    private ImageView imgEye;
    @InjectView(R.id.etPass)
    private EditText etPass;
    @InjectView(R.id.etPassTwo)
    private EditText etPassTwo;
    private boolean flag = true;
    private QMUITipDialog tipDialog;

    @Override
    public SetPWDContract.View getContract() {
        return new SetPWDContract.View() {
            @Override
            public void handlerResult(UserInfo t, int responseCode) {
                Log.e(TAG, "handlerResult:  SettingPWDActivity    " + responseCode);
                tipDialog.dismiss();
                if (200 == responseCode) {
                    Toasty.success(SettingPWDActivity.this, getString(R.string.str_long21), Toast.LENGTH_SHORT, true).show();
                    startActivity(LoginActivity.class);
                    // TODO: 2020/2/26  结束以前进程
                } else if (1010 == responseCode) {
                    Toasty.error(SettingPWDActivity.this, getString(R.string.str_long17), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1011) {
                    Toasty.error(SettingPWDActivity.this, getResources().getString(R.string.str_long01), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1012) {
                    Toasty.error(SettingPWDActivity.this, getString(R.string.str_long04), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1013) {
                    Toasty.error(SettingPWDActivity.this, getString(R.string.str_long05), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1014) {
                    Toasty.error(SettingPWDActivity.this, getString(R.string.str_long06), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1015) {
                    Toasty.error(SettingPWDActivity.this, getString(R.string.str_long07), Toast.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(SettingPWDActivity.this, getString(R.string.str_long08), Toast.LENGTH_SHORT, true).show();
                }
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == tipDialog) {
            tipDialog = new QMUITipDialog.Builder(SettingPWDActivity.this)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .setTipWord(getString(R.string.str_long11))
                    .create(false);
        }
    }

    @Override
    public SetPWDPresenter getPresenter() {
        return new SetPWDPresenter();
    }

    @OnClick({R.id.btnRegister, R.id.back, R.id.imgEye})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                if ("".equals(etPass.getText().toString())) {
                    etPass.setError(getString(R.string.str_long18));
                    Toasty.error(SettingPWDActivity.this, getString(R.string.str_long18), Toast.LENGTH_SHORT, true).show();
                } else if ("".equals(etPassTwo.getText().toString())) {
                    etPassTwo.setError(getString(R.string.str_long19));
                    Toasty.error(SettingPWDActivity.this, getString(R.string.str_long19), Toast.LENGTH_SHORT, true).show();
                } else if (!etPass.getText().toString().equals(etPassTwo.getText().toString())) {
                    etPass.setError(getString(R.string.str_long18));
                    etPassTwo.setError(getString(R.string.str_long19));
                    Toasty.error(SettingPWDActivity.this, getString(R.string.str_long20), Toast.LENGTH_SHORT, true).show();
                } else {
                    p.getContract().requestSetPWD(Date.userInfo.getAuthCode(), etPass.getText().toString(), Date.userInfo.getPhone());
                    tipDialog.show();
                }

                break;
            case R.id.back:
                finish();
                break;
            case R.id.imgEye:
                if (flag) {
                    imgEye.setImageResource(R.mipmap.ic_eye_open);
                    etPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPassTwo.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = false;
                } else {
                    imgEye.setImageResource(R.mipmap.ic_eye_close);
                    etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPassTwo.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = true;
                }
                etPass.setSelection(etPass.getText().length());
                etPassTwo.setSelection(etPassTwo.getText().length());
                break;
        }
    }

}
