package com.cdzp.farmnet.ui.activity;

import android.view.View;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.contract.pwdlogin.PWDLoginContract;
import com.cdzp.farmnet.contract.pwdlogin.PWDLoginPresenter;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.OnClick;

/**
 * 作者：张人文
 * 日期：2019/11/19 9:11
 * 邮箱：479696877@QQ.COM
 * 描述：密码登录
 */
@ContentView(R.layout.activity_pwdlogin)
public class PWDLoginActivity extends BaseView<PWDLoginPresenter, PWDLoginContract.View> {


    @Override
    public PWDLoginContract.View getContract() {
        return new PWDLoginContract.View() {
            @Override
            public void handlerResult(BaseEntity baseEntity) {

            }
        };
    }

    @Override
    public PWDLoginPresenter getPresenter() {
        return new PWDLoginPresenter();
    }

    @OnClick({R.id.tvForgetPWD,R.id.back,R.id.btnLogin,R.id.tvRegister})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.tvForgetPWD:
                startActivity(AuthenticationActivity.class);
                break;
            case R.id.btnLogin:
                startActivity(HomeActivity.class);
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
