package com.cdzp.farmnet.ui.activity;

import android.view.View;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.contract.setpwd.SetPWDContract;
import com.cdzp.farmnet.contract.setpwd.SetPWDPresenter;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.OnClick;

/**
 * 作者：张人文
 * 日期：2019/11/19 10:19
 * 邮箱：479696877@QQ.COM
 * 描述：忘记密码下设置密码
 */
@ContentView(R.layout.activity_setting_pwd)
public class SettingPWDActivity extends BaseView<SetPWDPresenter, SetPWDContract.View> {


    @Override
    public SetPWDContract.View getContract() {
        return new SetPWDContract.View() {
            @Override
            public void handlerResult(BaseEntity baseEntity) {

            }
        };
    }

    @Override
    public SetPWDPresenter getPresenter() {
        return new SetPWDPresenter();
    }

    @OnClick({R.id.btnRegister,R.id.back})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                startActivity(LoginActivity.class);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

}
