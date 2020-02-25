package com.cdzp.farmnet.api;

import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.bean.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ILoginRequestNetwork {
    @FormUrlEncoded
    @POST("api/Account/SendLoginOrRegCode")
    Observable<BaseEntity<UserInfo>> isPhoneAction(@Field("Phone") String phone);

    @FormUrlEncoded
    @POST("/api/Account/LoginOrRegByCode")
    Observable<BaseEntity<UserInfo>> requestLoginOrRegister(@Field("Phone") String phone, @Field("Code") String code);
}
