package com.cdzp.farmnet.api;

import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.bean.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IPWDLoginRequestNetwork {

    @FormUrlEncoded
    @POST("/api/Account/Login")
    Observable<BaseEntity<UserInfo>> requestPWDLogin(@Field("Username") String username, @Field("Password") String password);
}
