package com.muryno.community.server

import com.muryno.fundall.model.db.BaseData
import com.muryno.fundall.model.db.entity.User
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {


//    @Multipart
//    @POST("user/register")
//    fun register(
//        @Part("fname") fname: RequestBody, @Part("lname") lname: RequestBody, @Part("phone") phone: RequestBody,
//        @Part("email") email: RequestBody, @Part("password") password: RequestBody,
//        @Part picture: MultipartBody.Part
//    ): Call<BaseData<User>>
//

    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("email") email:String,@Field("password") paswrd:String,@Field("role") role:String)
            :Call<BaseData<User>>



    @FormUrlEncoded
    @POST("api/v1/register")
    fun signup(
        @Field("firstname") first_name: String, @Field("lastname") lastname: String,
        @Field("email") email: String,
        @Field("password_confirmation") password_confirmation: String, @Field("password") password: String
    ): Call<BaseData<User>>




    @Multipart
    @POST("user/image/update")
    fun profilePicUpdate(@Part image: MultipartBody.Part): Call<BaseData<User>>

}