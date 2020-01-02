package com.cdzp.farmnet.ui.activity;

import android.view.View;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.contract.authentication.AuthenticationContract;
import com.cdzp.farmnet.contract.authentication.AuthenticationPresenter;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.OnClick;

/**
 * 作者：张人文
 * 日期：2019/11/19 9:10
 * 邮箱：479696877@QQ.COM
 * 描述：验证身份
 */
@ContentView(R.layout.activity_authentication)
public class AuthenticationActivity extends BaseView<AuthenticationPresenter, AuthenticationContract.View> {

    @Override
    public AuthenticationContract.View getContract() {
        return new AuthenticationContract.View() {
            @Override
            public void handlerResult(BaseEntity baseEntity) {

            }
        };
    }

    @Override
    public AuthenticationPresenter getPresenter() {
        return new AuthenticationPresenter();
    }

    @OnClick({R.id.btnRegister, R.id.back})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                startActivity(SettingPWDActivity.class);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
