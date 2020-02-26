package com.cdzp.farmnet.ui.activity;

import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.contract.pwdlogin.PWDLoginContract;
import com.cdzp.farmnet.contract.pwdlogin.PWDLoginPresenter;
import com.cdzp.farmnet.utils.Date;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.InjectView;
import com.cmonbaby.ioc.core.annotation.OnClick;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import es.dmoral.toasty.Toasty;

/**
 * 作者：张人文
 * 日期：2019/11/19 9:11
 * 邮箱：479696877@QQ.COM
 * 描述：密码登录
 */
@ContentView(R.layout.activity_pwdlogin)
public class PWDLoginActivity extends BaseView<PWDLoginPresenter, PWDLoginContract.View> {

    @InjectView(R.id.etPhone)
    private EditText etPhone;
    @InjectView(R.id.etPass)
    private EditText etPass;
    private QMUITipDialog tipDialog;
    private char[] ac = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    @Override
    public PWDLoginContract.View getContract() {
        return new PWDLoginContract.View() {
            private static final String TAG = "PWDLoginActivity";

            @Override
            public void handlerResult(UserInfo userInfo, int responseCode) {
                Log.e(TAG, "handlerResult:     " + responseCode);
                tipDialog.dismiss();

                if (null != userInfo && 0 != responseCode) {
                    Date.userInfo = userInfo;
                }
                if (responseCode == 1010) {
                    startActivity(PWDSettingActivity.class);
                } else if (responseCode == 1012) {
                    Toasty.error(PWDLoginActivity.this, getString(R.string.str_long04), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1013) {
                    Toasty.error(PWDLoginActivity.this, getString(R.string.str_long05), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1014) {
                    Toasty.error(PWDLoginActivity.this, getString(R.string.str_long06), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 200) {
                    startActivity(HomeActivity.class);
                } else if (responseCode == 1015) {
                    Toasty.error(PWDLoginActivity.this, getString(R.string.str_long07), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1011) {
                    Toasty.error(PWDLoginActivity.this, getString(R.string.str_long01), Toast.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(PWDLoginActivity.this, getString(R.string.str_long08), Toast.LENGTH_SHORT, true).show();
                }
            }

        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        tipDialog = new QMUITipDialog.Builder(PWDLoginActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("请稍后...")
                .create(false);
        etPhone.setKeyListener(new DigitsKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL;
            }

            @Override
            protected char[] getAcceptedChars() {
                return ac;
            }
        });
    }

    @Override
    public PWDLoginPresenter getPresenter() {
        return new PWDLoginPresenter();
    }

    @OnClick({R.id.tvForgetPWD, R.id.back, R.id.btnLogin, R.id.tvRegister})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.tvForgetPWD:
                startActivity(ImportPhoneActivity.class);
                break;
            case R.id.btnLogin:
                if (!"".equals(etPhone.getText().toString())) {
                    if (!"".equals(etPass.getText().toString())) {
                        p.getContract().requestLogin(etPhone.getText().toString(), etPass.getText().toString());
                        tipDialog.show();
                    } else {
                        Toasty.error(PWDLoginActivity.this, getString(R.string.str_long12), Toast.LENGTH_SHORT, true).show();
                        etPass.setError(getString(R.string.str_long12));
                    }
                } else {
                    Toasty.error(PWDLoginActivity.this, getString(R.string.str_long13), Toast.LENGTH_SHORT, true).show();
                    etPhone.setError(getString(R.string.str_long13));
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.tvRegister:
                startActivity(RegisterActivity.class);
                break;
        }
    }
}
