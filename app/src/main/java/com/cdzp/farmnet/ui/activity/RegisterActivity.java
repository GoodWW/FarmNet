package com.cdzp.farmnet.ui.activity;

import android.view.View;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.contract.register.RegisterContract;
import com.cdzp.farmnet.contract.register.RegisterPresenter;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.OnClick;

@ContentView(R.layout.activity_redister)
public class RegisterActivity extends BaseView<RegisterPresenter, RegisterContract.View> {

    @OnClick({R.id.back,/*R.id.tvForgetPWD,R.id.btnLogin,*/R.id.btnRegister})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
       /*     case R.id.tvForgetPWD:
                startActivity(AuthenticationActivity.class);
                break;


            case R.id.btnLogin:
                startActivity(HomeActivity.class);
                break;*/

            case R.id.btnRegister:
                startActivity(LoginActivity.class);
                break;
        }
    }

    @Override
    public RegisterContract.View getContract() {
        return new RegisterContract.View() {
            @Override
            public void handlerRegisterResult(BaseEntity baseEntity) {

            }
        };
    }

    @Override
    public RegisterPresenter getPresenter() {
        return new RegisterPresenter();
    }
}
