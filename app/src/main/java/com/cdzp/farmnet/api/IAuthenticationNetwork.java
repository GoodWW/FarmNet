package com.cdzp.farmnet.api;

import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.bean.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IAuthenticationNetwork {
    @FormUrlEncoded
    @POST("/api/Account/SendChangePwdCode")
    Observable<BaseEntity<UserInfo>> requestCode(@Field("Phone") String phone);

    @FormUrlEncoded
    @POST("/api/Account/IsTrueChangePwdCode")
    Observable<BaseEntity<UserInfo>> judgeCode(@Field("Code") String code, @Field("Phone") String phone);
}
