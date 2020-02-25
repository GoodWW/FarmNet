package com.cdzp.farmnet.ui.activity;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.contract.authentication.AuthenticationContract;
import com.cdzp.farmnet.contract.authentication.AuthenticationPresenter;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.InjectView;
import com.cmonbaby.ioc.core.annotation.OnClick;

/**
 * 作者：张人文
 * 日期：2019/11/19 9:10
 * 邮箱：479696877@QQ.COM
 * 描述：验证身份
 */
@ContentView(R.layout.activity_authentication)
public class AuthenticationActivity extends BaseView<AuthenticationPresenter, AuthenticationContract.View> {

    private static final String TAG = "验证身份";
    @InjectView(R.id.tvPhone)
    private TextView tvPhone;


    @Override
    public AuthenticationContract.View getContract() {
        return new AuthenticationContract.View() {
            @Override
            public void handlerResult(BaseEntity baseEntity) {
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        String strResult = getString(R.string.hint_one) + "<font color='#EC7147'>" + addSpace(getIntent().getStringExtra("phone")) + "</font>";
        tvPhone.setText(Html.fromHtml(strResult));
    }

    @Override
    public AuthenticationPresenter getPresenter() {
        return new AuthenticationPresenter();
    }

    @OnClick({R.id.btnRegister, R.id.back})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:

//                startActivity(SettingPWDActivity.class);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private String addSpace(String bankAccountNumber) {
        if (bankAccountNumber == null) {
            return "";
        }
        char[] strs = bankAccountNumber.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
            if (i == 2) {
                sb.append(" ");
            } else if (i == 6) {
                sb.append(" ");
            }
//            if (i!=0&&(i+1)%4==0){
//                sb.append(" ");
//            }
        }

        String trim = sb.toString().trim();
        return trim;


    }

}
