package com.cdzp.farmnet.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.contract.addfarm.AddFarmContract;
import com.cdzp.farmnet.contract.addfarm.AddFarmPresenter;
import com.cdzp.farmnet.utils.cityselect.SelectAddressPop;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.InjectView;
import com.cmonbaby.ioc.core.annotation.OnClick;

import sad.zzq.com.selectaddressdemo.manager.AddressManager;
import sad.zzq.com.selectaddressdemo.tool.StringUtils;

/**
* 作者：张人文
* 日期：2019/12/21 9:01
* 邮箱：479696877@QQ.COM
* 描述：添加农场
*/
@ContentView(R.layout.activity_add_frame)
public class AddFrameActivity extends BaseView<AddFarmPresenter, AddFarmContract.View> {

    @InjectView(R.id.tvSite)
    private TextView tvSite;
    @InjectView(R.id.back)
    private ImageView back;
    private String provinceCode;
    private String cityCode;
    private String areaCode;
    private String townCode;


    @Override
    public AddFarmContract.View getContract() {
        return new AddFarmContract.View() {
            @Override

            public void handlerAddResult(BaseEntity baseEntity) {

            }
        };
    }

    @Override
    public AddFarmPresenter getPresenter() {
        return new AddFarmPresenter();
    }

    @OnClick({/*R.id.username, R.id.password,  R.id.imgSite,*/R.id.imgSite, R.id.tvSite, R.id.back})
    private void click(View view) {
        switch (view.getId()) {
//            case R.id.username:
//                Toast.makeText(LoginActivity.this, "uesrname", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.btnLogin:
//                startActivity(HomeActivity.class);
//                break;
            case R.id.imgSite:
                startActivityForResult(MapActivity.class,200);
                break;
            case R.id.back:
                finish();
                break;
            case R.id.tvSite:
                showPop();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("", "返回外面:     " +resultCode+"===="+requestCode);
        //此处可以根据两个Code进行判断，本页面和结果页面跳过来的值
        if (requestCode == 200 && resultCode == 300) {
            String result = data.getStringExtra("result");

            Log.e("", "返回:     " +result);
            tvSite.setText(result);
        }
    }



    public interface SelectAddresFinish {
        void finish(String provinceCode, String cityCode, String areaCode, String townCode);
    }

    private void showPop() {
        SelectAddressPop pop = new SelectAddressPop();
        pop.setSelectAddresFinish(new SelectAddresFinish() {
            @Override
            public void finish(String pCode, String cCode, String aCode, String tCode) {
                //选中地址完成
                provinceCode = pCode;
                cityCode = cCode;
                areaCode = aCode;
                if (StringUtils.isNoEmpty(tCode)) {
                    townCode = tCode;
                    String addr = AddressManager.newInstance().getAddress(pCode, cCode, aCode, tCode);
                    tvSite.setText(addr);
                } else {
                    String addr = AddressManager.newInstance().getAddress(pCode, cCode, aCode);
                    tvSite.setText(addr);
                }
            }
        });
        pop.setAddress(provinceCode, cityCode, areaCode, townCode);
        pop.show(getFragmentManager(), "address");
    }
}
