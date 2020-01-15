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
    private static final String TAG = "登录";
    @InjectView(R.id.etPhone)
    private EditText etPhone;
    @InjectView(R.id.etCode)
    private EditText etCode;
    @InjectView(R.id.btnLogin)
    private Button btnLogin;
    @InjectView(R.id.tvTime)
    private TextView tvTime;

    QMUITipDialog tipDialog;

    @Override
    public LoginContract.View getContract() {
        return new LoginContract.View() {
            @Override
            public void handlerIsPhoneResult(boolean userInfo) {
                Log.e("  ", "数据返回    " + userInfo);
                tipDialog.dismiss();
                if (userInfo) btnLogin.setText(getResources().getString(R.string.str_login));
                else btnLogin.setText(getResources().getString(R.string.str_register));
            }

            @Override
            public void handlerLoginOrRegisterResult(UserInfo userInfo, int flag) {
                Log.e(TAG, "handlerLoginOrRegisterResult: " + userInfo.toString() + "    ===" + flag);
                Date.userInfo = userInfo;
                if (flag == 1) {
                    startActivity(PWDSettingActivity.class);
                } else if (flag == 2) {
                    startActivity(HomeActivity.class);
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        tipDialog = new QMUITipDialog.Builder(LoginActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("请稍后...")
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
//                        Log.e("  ", "数据: "+ s.toString());
                        requestCode(s.toString());
                    } else {
                        Toasty.error(LoginActivity.this, "手机号不正确", Toast.LENGTH_SHORT, true).show();
                        etPhone.setError("手机号不正确");
                    }
                }
            }
        });
    }

    private void requestCode(String s) {
        p.getContract().requestIsPhone(s);
        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(LoginActivity.this, tvTime, 60000, 1000); //倒计时1分钟
        mCountDownTimerUtils.start();
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    @OnClick({/*R.id.username, R.id.password, */ R.id.tvTime, R.id.btnLogin, R.id.back, R.id.tvPassLogin})
    private void click(View view) {
        switch (view.getId()) {
//            case R.id.username:
//                Toast.makeText(LoginActivity.this, "uesrname", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.btnLogin:
                if (RegexStringUtil.MatchTelNum(etPhone.getText().toString())) {
                    if (!"".equals(etCode.getText().toString()) && etCode.getText().length() == 6) {
                        if (btnLogin.getText().toString().equals(getResources().getString(R.string.str_register))) {//注册
                            p.getContract().requestLoginOrRegister(etPhone.getText().toString(), etCode.getText().toString(), 1);
                        } else if (btnLogin.getText().toString().equals(getResources().getString(R.string.str_login))) {//登录
                            p.getContract().requestLoginOrRegister(etPhone.getText().toString(), etCode.getText().toString(), 2);
                        }
                    } else {
                        Toasty.error(LoginActivity.this, "请输入六位的验证码", Toast.LENGTH_SHORT, true).show();
                        etCode.setError("请输入六位的验证码");
                    }
                } else {
                    Toasty.error(LoginActivity.this, "手机号不正确", Toast.LENGTH_SHORT, true).show();
                    etPhone.setError("手机号不正确");
                }
                break;
            case R.id.tvTime:
                if (RegexStringUtil.MatchTelNum(etPhone.getText().toString())) {
                    tipDialog.show();
                    requestCode(etPhone.getText().toString());
                } else {
                    etPhone.setError("手机号不正确");
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.tvPassLogin:
                startActivity(PWDLoginActivity.class);
                break;
        }
    }
}
