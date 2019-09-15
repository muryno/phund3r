package com.muryno.fundall.controller.presenter

import android.util.Patterns
import com.muryno.fundall.controller.view.RegView
import com.muryno.fundall.model.db.BaseData
import com.muryno.fundall.model.db.entity.Base
import com.muryno.fundall.model.db.entity.Infor
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

        RetrofitClient().getApi().signup(fname,lname,email,confirm_pass,password).enqueue(object :Callback<BaseData<Infor>>{
            override fun onFailure(call: Call<BaseData<Infor>>, t: Throwable) {
                Debug.Log(t.message!!)
                callback.loadingFailed("Connection failed.. please try again!!")            }

            override fun onResponse(
                call: Call<BaseData<Infor>>,
                response: Response<BaseData<Infor>>
            ) {
                try {

                    if (response.body() != null && response.body()?.error == null) {
                        callback.loadingSuccessful(response.body()?.success?.message)
                    } else {
                        callback.loadingFailed(response.body()?.error?.message)

                    }
                }catch (e : Exception){
                    callback.loadingFailed("Network problem....")

                }
            }

        })




    }
}