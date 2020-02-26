package com.cdzp.farmnet.ui.activity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.contract.authentication.AuthenticationContract;
import com.cdzp.farmnet.contract.authentication.AuthenticationPresenter;
import com.cdzp.farmnet.utils.CountDownTimerUtils;
import com.cdzp.farmnet.utils.Date;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.InjectView;
import com.cmonbaby.ioc.core.annotation.OnClick;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import es.dmoral.toasty.Toasty;

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
    @InjectView(R.id.tvTime)
    private TextView tvTime;
    @InjectView(R.id.etCode)
    private TextView etCode;
    private CountDownTimerUtils mCountDownTimerUtils; //倒计时1分钟
    private QMUITipDialog tipDialog;
    private String strPhone;

    @Override
    public AuthenticationContract.View getContract() {
        return new AuthenticationContract.View() {
            @Override
            public void handlerResult(UserInfo userInfo, int responseCode) {
                Log.e(TAG, "handlerResult:   " + responseCode);
                tipDialog.dismiss();
                if (200 == responseCode) {
                    action();
                    Toasty.success(AuthenticationActivity.this, getResources().getString(R.string.str_long03), Toast.LENGTH_SHORT, true).show();
                } else if (1010 == responseCode) {
                    Toasty.error(AuthenticationActivity.this, getString(R.string.str_long17), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1011) {
                    Toasty.error(AuthenticationActivity.this, getResources().getString(R.string.str_long01), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1012) {
                    Toasty.error(AuthenticationActivity.this, getString(R.string.str_long04), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1013) {
                    Toasty.error(AuthenticationActivity.this, getString(R.string.str_long05), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1014) {
                    Toasty.error(AuthenticationActivity.this, getString(R.string.str_long06), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1015) {
                    Toasty.error(AuthenticationActivity.this, getString(R.string.str_long07), Toast.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(AuthenticationActivity.this, getString(R.string.str_long08), Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void handlerJudgeCodeResult(UserInfo userInfo, int responseCode) {
                Log.e(TAG, "handlerJudgeCodeResult:   " + responseCode);

                tipDialog.dismiss();
                if (200 == responseCode) {
                    Date.userInfo = userInfo;
                    Date.userInfo.setPhone(strPhone);
                    startActivity(SettingPWDActivity.class);
                } else if (1010 == responseCode) {
                    Toasty.success(AuthenticationActivity.this, getResources().getString(R.string.str_long03), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1011) {
                    Toasty.error(AuthenticationActivity.this, getResources().getString(R.string.str_long01), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1012) {
                    Toasty.error(AuthenticationActivity.this, getString(R.string.str_long04), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1013) {
                    Toasty.error(AuthenticationActivity.this, getString(R.string.str_long05), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1014) {
                    Toasty.error(AuthenticationActivity.this, getString(R.string.str_long06), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1015) {
                    Toasty.error(AuthenticationActivity.this, getString(R.string.str_long07), Toast.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(AuthenticationActivity.this, getString(R.string.str_long08), Toast.LENGTH_SHORT, true).show();
                }
            }
        };
    }

    private void action() {
        if (null != mCountDownTimerUtils) {
            mCountDownTimerUtils.onFinish();
        } else {
            mCountDownTimerUtils = new CountDownTimerUtils(AuthenticationActivity.this, tvTime, 60000, 1000);
        }
        mCountDownTimerUtils.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        strPhone = getIntent().getStringExtra("phone");
        String strResult = getString(R.string.hint_one) + "<font color='#EC7147'>" + addSpace(strPhone) + "</font>";
        tvPhone.setText(Html.fromHtml(strResult));

        if (null == tipDialog) {
            tipDialog = new QMUITipDialog.Builder(AuthenticationActivity.this)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .setTipWord(getString(R.string.str_long11))
                    .create(false);
        }
        p.getContract().requestCode(strPhone);
        tipDialog.show();
    }

    @Override
    public AuthenticationPresenter getPresenter() {
        return new AuthenticationPresenter();
    }

    @OnClick({R.id.btnRegister, R.id.back, R.id.tvTime})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                if (!"".equals(etCode.getText().toString()) && etCode.getText().toString().length() == 6) {
                    p.getContract().judgeCode(etCode.getText().toString(), strPhone);
                    tipDialog.show();
                } else {
                    Toasty.error(AuthenticationActivity.this, getString(R.string.str_long10), Toast.LENGTH_SHORT, true).show();
                }
                break;
            case R.id.tvTime:
                if (11 == strPhone.length()) {
                    p.getContract().requestCode(strPhone);
                    tipDialog.show();
                }
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
        }
        String trim = sb.toString().trim();
        return trim;
    }
}
