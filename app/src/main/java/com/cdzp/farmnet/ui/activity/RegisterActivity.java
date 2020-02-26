package com.cdzp.farmnet.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.bean.UserInfo;
import com.cdzp.farmnet.contract.register.RegisterContract;
import com.cdzp.farmnet.contract.register.RegisterPresenter;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.InjectView;
import com.cmonbaby.ioc.core.annotation.OnClick;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import es.dmoral.toasty.Toasty;

/**
 * 作者：张人文
 * 日期：2020/2/26 13:55
 * 邮箱：479696877@QQ.COM
 * 描述：注册新账号
 */
@ContentView(R.layout.activity_redister)
public class RegisterActivity extends BaseView<RegisterPresenter, RegisterContract.View> {
    private static final String TAG = "注册新账号";
    @InjectView(R.id.etAccount)
    private EditText etAccount;
    @InjectView(R.id.etPass)
    private EditText etPass;
    @InjectView(R.id.etPassTwo)
    private EditText etPassTwo;
    @InjectView(R.id.imgEye)
    private ImageView imgEye;
    private char[] ac = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private boolean flag = true;
    private QMUITipDialog tipDialog;

    @Override
    public RegisterContract.View getContract() {
        return new RegisterContract.View() {
            @Override
            public void handlerRegisterResult(UserInfo t, int responseCode) {
                Log.e(TAG, "handlerResult:  SettingPWDActivity    " + responseCode);
                tipDialog.dismiss();
                if (200 == responseCode) {
                    Toasty.success(RegisterActivity.this, getString(R.string.str_long23), Toast.LENGTH_SHORT, true).show();
                    startActivity(LoginActivity.class);
                    // TODO: 2020/2/26  结束以前进程
                } else if (1010 == responseCode) {
                    Toasty.error(RegisterActivity.this, getString(R.string.str_long17), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1011) {
                    Toasty.error(RegisterActivity.this, getResources().getString(R.string.str_long01), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1012) {
                    Toasty.error(RegisterActivity.this, getString(R.string.str_long04), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1013) {
                    Toasty.error(RegisterActivity.this, getString(R.string.str_long05), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1014) {
                    Toasty.error(RegisterActivity.this, getString(R.string.str_long06), Toast.LENGTH_SHORT, true).show();
                } else if (responseCode == 1015) {
                    Toasty.error(RegisterActivity.this, getString(R.string.str_long07), Toast.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(RegisterActivity.this, getString(R.string.str_long08), Toast.LENGTH_SHORT, true).show();
                }
            }
        };
    }

    @OnClick({R.id.back,/*R.id.tvForgetPWD,*/R.id.imgEye, R.id.btnRegister})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
       /*     case R.id.tvForgetPWD:
                startActivity(AuthenticationActivity.class);
                break;*/


            case R.id.imgEye:
                if (flag) {
                    imgEye.setImageResource(R.mipmap.ic_eye_open);
                    etPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPassTwo.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    flag = false;
                } else {
                    imgEye.setImageResource(R.mipmap.ic_eye_close);
                    etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPassTwo.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = true;
                }
                etPass.setSelection(etPass.getText().length());
                etPassTwo.setSelection(etPassTwo.getText().length());
                break;
            case R.id.btnRegister:
                if ("".equals(etAccount.getText().toString())) {
                    Toasty.error(RegisterActivity.this, getString(R.string.str_account), Toast.LENGTH_SHORT, true).show();
                    etAccount.setError(getString(R.string.str_account));
                } else if ("".equals(etPass.getText().toString())) {
                    Toasty.error(RegisterActivity.this, getString(R.string.str_pwd), Toast.LENGTH_SHORT, true).show();
                    etPass.setError(getString(R.string.str_pwd));
                } else if ("".equals(etPassTwo.getText().toString())) {
                    Toasty.error(RegisterActivity.this, getString(R.string.str_pwd_again), Toast.LENGTH_SHORT, true).show();
                    etPassTwo.setError(getString(R.string.str_pwd_again));
                } else if (!etPass.getText().toString().equals(etPassTwo.getText().toString())) {
                    Toasty.error(RegisterActivity.this, getString(R.string.str_long20), Toast.LENGTH_SHORT, true).show();
                    etPass.setError(getString(R.string.str_pwd));
                    etPassTwo.setError(getString(R.string.str_pwd_again));
                } else {
                    p.getContract().requestRegister(etAccount.getText().toString(),etPass.getText().toString());
                    tipDialog.show();
                }
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == tipDialog) {
            tipDialog = new QMUITipDialog.Builder(RegisterActivity.this)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .setTipWord(getString(R.string.str_long11))
                    .create(false);
        }
        etAccount.setKeyListener(new DigitsKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL;
            }

            @Override
            protected char[] getAcceptedChars() {
                return ac;
            }
        });

        etAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    if (!"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(s)) {
                        Toasty.error(RegisterActivity.this, getString(R.string.str_long22),
                                Toast.LENGTH_SHORT, true).show();
                        s.delete(0, 1);
                    }
                }
                Log.e(TAG, "账号内容:       " + s + "=====" + s.length());
            }
        });
    }


    @Override
    public RegisterPresenter getPresenter() {
        return new RegisterPresenter();
    }
}
