package com.cdzp.farmnet.api;

import com.cdzp.farmnet.bean.BaseEntity;
import com.cdzp.farmnet.bean.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IRegisterNetwork {
    @FormUrlEncoded
    @POST("/api/Account/Register")
    Observable<BaseEntity<UserInfo>> requestRegister(@Field("Username") String username, @Field("Password") String password);
}
