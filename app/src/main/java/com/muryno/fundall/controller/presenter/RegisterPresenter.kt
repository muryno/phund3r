package com.muryno.fundall.controller.presenter

import android.util.Patterns
import com.muryno.fundall.controller.view.RegView
import com.muryno.fundall.model.db.BaseData
import com.muryno.fundall.model.db.entity.Base
import com.muryno.fundall.model.server.RetrofitClient
import com.muryno.fundall.utils.Debug
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenter(var callback: RegView) {


    fun validate (fname :String, lname : String, email : String, password : String, confirm_pass : String){
        callback.loadingStart()

        if (fname.isEmpty()) {
            callback.fnameError("Enter a valid name!")
            callback.loadingFailed("")
            return
        }
        if (lname.isEmpty()) {
            callback.fnameError("Enter a valid name!")
            callback.loadingFailed("")
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            callback.emailError("Enter a valid Email")
            callback.loadingFailed("")
            return
        }


        if (password.isEmpty()) {
            callback.passError("Password cannot be empty")
            callback.loadingFailed("")
            return
        }

        if (confirm_pass.isEmpty()) {
            callback.passError("Field cannot be empty")
            callback.loadingFailed("")
            return
        }

        if (confirm_pass != password) {
            callback.passError("password not equal")
            callback.loadingFailed("")
            return
        }

        RetrofitClient().getApi().signup(fname,lname,email,confirm_pass,password).enqueue(object :Callback<BaseData<Base>>{
            override fun onFailure(call: Call<BaseData<Base>>, t: Throwable) {
                Debug.Log(t.message!!)
                callback.loadingFailed("Connection failed.. please try again!!")            }

            override fun onResponse(
                call: Call<BaseData<Base>>,
                response: Response<BaseData<Base>>
            ) {
                val data = response.body()?.data as Base

                if(response.body()!=null && data.error ==null){
                    callback.loadingSuccessful(data.success?.message)
                }else{
                    callback.loadingFailed(data.error?.message)

                }
            }

        })




    }
}