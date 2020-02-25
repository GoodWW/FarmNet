package com.cdzp.farmnet.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.contract.login.LoginContract;
import com.cdzp.farmnet.contract.login.LoginPresenter;
import com.cdzp.farmnet.utils.CountDownTimerUtils;
import com.cdzp.farmnet.utils.Date;
import com.cdzp.farmnet.utils.RegexStringUtil;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.InjectView;
import com.cmonbaby.ioc.core.annotation.OnClick;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import es.dmoral.toasty.Toasty;

/**
 * 作者：张人文
 * 日期：2019/11/19 9:11
 * 邮箱：479696877@QQ.COM
 * 描述：登录
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseView<LoginPresenter, LoginContract.View> {
    private static final String TAG = "Login";
    @InjectView(R.id.etPhone)
    private EditText etPhone;
    @InjectView(R.id.etCode)
    private EditText etCode;
    @InjectView(R.id.btnLogin)
    private Button btnLogin;
    @InjectView(R.id.tvTime)
    private TextView tvTime;
    private QMUITipDialog tipDialog;

    @Override
    public LoginContract.View getContract() {
        return new LoginContract.View() {
            @Override
            public void handlerIsPhoneResult(int responseCode) {//0 1 2 已注册  未注册 请求网络失败
                Log.e("  ", "LoginActivity   数据返回    " + responseCode);
                tipDialog.dismiss();
                if (200 == responseCode) {
                    btnLogin.setText(getResources().getString(R.string.str_login));
                    Toasty.success(LoginActivity.this, getResources().getString(R.string.str_long03), Toast.LENGTH_SHORT, true).show();
                } else if (1010 == responseCode) {

                    btnLogin.setText(getResources().getString(R.string.str_register));
                    Toasty.success(LoginActivity.this, getResources().getString(R.string.str_long03), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1011) {
                    Toasty.error(LoginActivity.this, getResources().getString(R.string.str_long01), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1012) {
                    Toasty.error(LoginActivity.this, getString(R.string.str_long04), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1013) {
                    Toasty.error(LoginActivity.this, getString(R.string.str_long05), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1014) {
                    Toasty.error(LoginActivity.this, getString(R.string.str_long06), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1015) {
                    Toasty.error(LoginActivity.this, getString(R.string.str_long07), Toast.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(LoginActivity.this, getString(R.string.str_long08), Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void handlerLoginOrRegisterResult(UserInfo userInfo, int responseCode) {
                if (null != userInfo && 0 != responseCode) {
                    Log.e(TAG, "LoginActivity    handlerLoginOrRegisterResult: " + userInfo.toString() + "    ===" + responseCode);
                    Date.userInfo = userInfo;
                    if (responseCode == 1010) {
                        startActivity(PWDSettingActivity.class);
                    } else if (responseCode == 1011) {
                        Toasty.error(LoginActivity.this, getResources().getString(R.string.str_long01), Toast.LENGTH_SHORT, true).show();
                    } else if (responseCode == 1012) {
                        Toasty.error(LoginActivity.this, getString(R.string.str_long04), Toast.LENGTH_SHORT, true).show();
                    } else if (responseCode == 1013) {
                        Toasty.error(LoginActivity.this, getString(R.string.str_long05), Toast.LENGTH_SHORT, true).show();
                    } else if (responseCode == 1014) {
                        Toasty.error(LoginActivity.this, getString(R.string.str_long06), Toast.LENGTH_SHORT, true).show();
                    } else if (responseCode == 200) {
                        startActivity(HomeActivity.class);
                    } else if (responseCode == 1015) {
                        Toasty.error(LoginActivity.this, getString(R.string.str_long07), Toast.LENGTH_SHORT, true).show();
                    }
                } else {
                    Toasty.error(LoginActivity.this, getString(R.string.str_long08), Toast.LENGTH_SHORT, true).show();
                }
            }
        };
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.white;
    }

    @Override
    protected void onResume() {
        super.onResume();
        tipDialog = new QMUITipDialog.Builder(LoginActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(getString(R.string.str_long11))
                .create(false);

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 10) {
                    if (RegexStringUtil.MatchTelNum(s.toString())) {
                        tipDialog.show();
                        Log.e(TAG, "数据: " + s.toString());
                        requestCode(s.toString());
                    } else {
                        Toasty.error(LoginActivity.this, getString(R.string.str_long09), Toast.LENGTH_SHORT, true).show();
                        etPhone.setError(getString(R.string.str_long09));
                    }
                }
            }
        });
    }

    private CountDownTimerUtils mCountDownTimerUtils; //倒计时1分钟

    private void requestCode(String s) {
        p.getContract().requestIsPhone(s);
        if (null != mCountDownTimerUtils) {
            mCountDownTimerUtils.onFinish();
        } else {
            mCountDownTimerUtils = new CountDownTimerUtils(LoginActivity.this, tvTime, 60000, 1000);
        }
        mCountDownTimerUtils.start();
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    @OnClick({/*R.id.username, R.id.password, */ R.id.tvTime, R.id.btnLogin, /*R.id.back, */R.id.tvPassLogin})
    private void click(View view) {
        switch (view.getId()) {
//            case R.id.username:
//                Toast.makeText(LoginActivity.this, "uesrname", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.btnLogin:
                if (RegexStringUtil.MatchTelNum(etPhone.getText().toString())) {
                    if (!"".equals(etCode.getText().toString()) && etCode.getText().length() == 6) {
                        if (btnLogin.getText().toString().equals(getResources().getString(R.string.str_register))) {//注册
                            p.getContract().requestLoginOrRegister(etPhone.getText().toString(), etCode.getText().toString());
                        } else if (btnLogin.getText().toString().equals(getResources().getString(R.string.str_login))) {//登录
                            p.getContract().requestLoginOrRegister(etPhone.getText().toString(), etCode.getText().toString());
                        }
                    } else {
                        Toasty.error(LoginActivity.this, getString(R.string.str_long10), Toast.LENGTH_SHORT, true).show();
                        etCode.setError(getString(R.string.str_long10));
                    }
                } else {
                    Toasty.error(LoginActivity.this,  getString(R.string.str_long06), Toast.LENGTH_SHORT, true).show();
                    etPhone.setError(getString(R.string.str_long06));
                }
                break;
            case R.id.tvTime:
                if (RegexStringUtil.MatchTelNum(etPhone.getText().toString())) {
                    tipDialog.show();
                    requestCode(etPhone.getText().toString());
                } else {
                    etPhone.setError(getString(R.string.str_long06));
                }
                break;
          /*  case R.id.back:
                finish();
                break;*/
            case R.id.tvPassLogin:
                startActivity(PWDLoginActivity.class);
                break;
        }
    }
}
