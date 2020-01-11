package com.cdzp.farmnet.contract.login;

import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.bean.TestBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IRequestNetwork {

    // 请求注册 功能  todo 耗时操作 ---> OkHttp
    @FormUrlEncoded
    @POST("api/Account/SendLoginOrRegCode")
    Observable<BaseEntity<BaseEntity<TestBean>>> isPhoneAction(@Field("Phone") String phone);
}
