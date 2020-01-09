package com.cdzp.farmnet.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.contract.login.LoginContract;
import com.cdzp.farmnet.contract.login.LoginPresenter;
import com.cdzp.farmnet.utils.CountDownTimerUtils;
import com.cdzp.farmnet.utils.RegexStringUtil;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.InjectView;
import com.cmonbaby.ioc.core.annotation.OnClick;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

/**
 * 作者：张人文
 * 日期：2019/11/19 9:11
 * 邮箱：479696877@QQ.COM
 * 描述：登录
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseView<LoginPresenter, LoginContract.View> {
    @InjectView(R.id.etPhone)
    EditText etPhone;
    @InjectView(R.id.tvTime)
    private TextView tvTime;
    QMUITipDialog tipDialog;

    @Override
    public LoginContract.View getContract() {
        return new LoginContract.View<UserInfo>() {
            @Override
            public void handlerResult(UserInfo userInfo) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        tipDialog = new QMUITipDialog.Builder(LoginActivity.this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
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
//                        tipDialog.show();

                        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(LoginActivity.this ,tvTime, 60000, 1000); //倒计时1分钟
                        mCountDownTimerUtils.start();
                    } else {
                        etPhone.setError("手机号不正确");
                    }
                }
            }
        });
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    @OnClick({/*R.id.username, R.id.password,  R.id.loading,*/R.id.btnLogin, R.id.back, R.id.tvPassLogin})
    private void click(View view) {
        switch (view.getId()) {
//            case R.id.username:
//                Toast.makeText(LoginActivity.this, "uesrname", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.btnLogin:
                startActivity(HomeActivity.class);
                break;
//            case R.id.password:
//                Toast.makeText(LoginActivity.this, "password", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.back:
                finish();
                Toast.makeText(LoginActivity.this, "back", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvPassLogin:
                startActivity(PWDLoginActivity.class);
                break;
        }

    }


}
