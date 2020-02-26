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
import com.cdzp.farmnet.contract.pwdsetting.PWDSettingContract;
import com.cdzp.farmnet.contract.pwdsetting.PWDSettingPresenter;
import com.cdzp.farmnet.utils.Date;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.InjectView;
import com.cmonbaby.ioc.core.annotation.OnClick;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import es.dmoral.toasty.Toasty;

/**
 * 作者：张人文
 * 日期：2019/11/19 9:12
 * 邮箱：479696877@QQ.COM
 * 描述：首次登录设置密码
 */
@ContentView(R.layout.activity_pwdsetting)
public class PWDSettingActivity extends BaseView<PWDSettingPresenter, PWDSettingContract.View> {
    private static final String TAG = "设置密码";
    @InjectView(R.id.etPass)
    private EditText etPass;
    @InjectView(R.id.etPassTwo)
    private EditText etPassTwo;
    @InjectView(R.id.imgEye)
    private ImageView imgEye;
    private boolean flag = true;
    private QMUITipDialog tipDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == tipDialog) {
            tipDialog = new QMUITipDialog.Builder(PWDSettingActivity.this)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .setTipWord(getString(R.string.str_long11))
                    .create(false);
        }
    }

    @Override
    public PWDSettingContract.View getContract() {
        return new PWDSettingContract.View() {
            @Override
            public void handlerSettingPWDResult(int responseCode) {
                tipDialog.dismiss();
                Log.e(TAG, "handlerResult:  SettingPWDActivity    " + responseCode);
                tipDialog.dismiss();
                if (200 == responseCode) {
                    Toasty.success(PWDSettingActivity.this, getString(R.string.str_long24), Toast.LENGTH_SHORT, true).show();
                    startActivity(HomeActivity.class);
                } else if (1010 == responseCode) {
                    Toasty.error(PWDSettingActivity.this, getString(R.string.str_long17), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1011) {
                    Toasty.error(PWDSettingActivity.this, getResources().getString(R.string.str_long01), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1012) {
                    Toasty.error(PWDSettingActivity.this, getString(R.string.str_long04), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1013) {
                    Toasty.error(PWDSettingActivity.this, getString(R.string.str_long05), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1014) {
                    Toasty.error(PWDSettingActivity.this, getString(R.string.str_long06), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1015) {
                    Toasty.error(PWDSettingActivity.this, getString(R.string.str_long07), Toast.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(PWDSettingActivity.this, getString(R.string.str_long08), Toast.LENGTH_SHORT, true).show();
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
                etPass.setSelection(etPass.getText().length());
                etPassTwo.setSelection(etPassTwo.getText().length());
                break;
            case R.id.btnRegister:
                if ("".equals(etPass.getText().toString())) {
                    Toasty.error(PWDSettingActivity.this, getString(R.string.str_pwd), Toast.LENGTH_SHORT, true).show();
                    etPass.setError(getString(R.string.str_pwd));
                } else if ("".equals(etPassTwo.getText().toString())) {
                    Toasty.error(PWDSettingActivity.this, getString(R.string.str_long15), Toast.LENGTH_SHORT, true).show();
                    etPassTwo.setError(getString(R.string.str_long15));
                } else if (!etPass.getText().toString().equals(etPassTwo.getText().toString())) {
                    Toasty.error(PWDSettingActivity.this, getString(R.string.str_long20), Toast.LENGTH_SHORT, true).show();
                    etPass.setError(getString(R.string.str_pwd));
                    etPassTwo.setError(getString(R.string.str_long19));
                } else {
                    p.getContract().requestSettingPWD(Date.userInfo.getPhone(), etPass.getText().toString(), Date.userInfo.getAuthCode());
                    tipDialog.show();
                }
                break;
            case R.id.back:
                finish();
                break;

        }

    }
}
