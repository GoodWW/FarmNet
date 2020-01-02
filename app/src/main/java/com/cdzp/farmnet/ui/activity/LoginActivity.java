package com.cdzp.farmnet.ui.activity;

import android.view.View;
import android.widget.Toast;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.contract.login.LoginContract;
import com.cdzp.farmnet.contract.login.LoginPresenter;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.OnClick;

/**
 * 作者：张人文
 * 日期：2019/11/19 9:11
 * 邮箱：479696877@QQ.COM
 * 描述：登录
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseView<LoginPresenter, LoginContract.View> {

    @Override
    public LoginContract.View getContract() {
        return new LoginContract.View<UserInfo>() {
            @Override
            public void handlerResult(UserInfo userInfo) {

            }
        };
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
