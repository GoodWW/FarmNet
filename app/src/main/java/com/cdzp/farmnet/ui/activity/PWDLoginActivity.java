package com.cdzp.farmnet.ui.activity;

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
                    Toasty.error(PWDLoginActivity.this, "验证码错误", Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1013) {
                    Toasty.error(PWDLoginActivity.this, "用户已存在", Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1014) {
                    Toasty.error(PWDLoginActivity.this, "已经发送过短信了", Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 200) {
                    startActivity(HomeActivity.class);
                } else if (responseCode == 1015) {
                    Toasty.error(PWDLoginActivity.this, "验证码已超时", Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1011) {
                    Toasty.error(PWDLoginActivity.this, "账号或者密码错误", Toast.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(PWDLoginActivity.this, "请求网络失败", Toast.LENGTH_SHORT, true).show();
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
    }

    @Override
    public PWDLoginPresenter getPresenter() {
        return new PWDLoginPresenter();
    }

    @OnClick({R.id.tvForgetPWD, R.id.back, R.id.btnLogin, R.id.tvRegister})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.tvForgetPWD:
                startActivity(AuthenticationActivity.class);
                break;
            case R.id.btnLogin:
                if (!"".equals(etPhone.getText().toString())) {
                    if (!"".equals(etPass.getText().toString())) {
                        p.getContract().requestLogin(etPhone.getText().toString(), etPass.getText().toString());
                        tipDialog.show();
                    } else {
                        Toasty.error(PWDLoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT, true).show();
                        etPass.setError("密码不能为空");
                    }
                } else {
                    Toasty.error(PWDLoginActivity.this, "手机号不正确", Toast.LENGTH_SHORT, true).show();
                    etPhone.setError("用户名不能为空");
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
