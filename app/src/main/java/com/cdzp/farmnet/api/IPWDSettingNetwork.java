package com.cdzp.farmnet.api;

import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.bean.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IPWDSettingNetwork {
    @FormUrlEncoded
    @POST("/api/Account/SetPassword")
    Observable<BaseEntity<UserInfo>> requestPWDSetting(@Field("AuthCode") String authCode, @Field("Password") String password
     ,@Field("Phone") String phone);
}
