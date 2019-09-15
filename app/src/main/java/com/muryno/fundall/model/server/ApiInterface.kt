package com.muryno.fundall.model.server

import com.muryno.fundall.model.db.BaseData
import com.muryno.fundall.model.db.entity.Base
import com.muryno.fundall.model.db.entity.Infor
import com.muryno.fundall.model.db.entity.User
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {




    @FormUrlEncoded
    @POST("api/v1/login")
    fun login(@Field("email") email:String,@Field("password") paswrd:String)
            :Call<BaseData<Infor>>



    @FormUrlEncoded
    @POST("api/v1/register")
    fun signup(
        @Field("firstname") first_name: String, @Field("lastname") lastname: String,
        @Field("email") email: String,
        @Field("password_confirmation") password_confirmation: String, @Field("password") password: String
    ): Call<BaseData<Infor>>




    @Multipart
    @POST("api/v1/base/avatar")
    fun profilePicUpdate(@Part image: MultipartBody.Part): Call<BaseData<Infor>>

    @GET("api/v1/base/profile")
    fun getUser():  Call<BaseData<Infor>>

}