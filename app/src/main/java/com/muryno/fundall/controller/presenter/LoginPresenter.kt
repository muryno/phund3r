package com.muryno.fundall.controller.presenter

import android.util.Patterns
import com.muryno.fundall.model.db.MemoryManager
import com.muryno.fundall.controller.view.LoginView
import com.muryno.fundall.model.db.BaseData
import com.muryno.fundall.model.db.entity.Base
import com.muryno.fundall.model.db.entity.Infor
import com.muryno.fundall.model.server.RetrofitClient
import com.muryno.fundall.utils.Debug
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginPresenter(private var callback: LoginView?) {

    fun validate(email:String,password:String){
        callback?.loadingStart()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            callback?.loadingFailed("")
            callback?.invalidEmail("Enter a valid email address!")
            return
        }

        if (password.isEmpty()) {
            callback?.loadingFailed("")
            callback?.invalidPass("Enter a valid password!")
            return
        }


        RetrofitClient().getApi().login(email,password).enqueue(object :Callback<BaseData<Infor>>{
            override fun onFailure(call: Call<BaseData<Infor>>, t: Throwable) {
                Debug.Log(t.message!!)
                callback?.loadingFailed("Connection failed.. please try again!!")
            }

            override fun onResponse(
                call: Call<BaseData<Infor>>,
                response: Response<BaseData<Infor>>
            ) {


                try {

                    if (response.body() != null ){
                        if (response.body()?.error == null) {
                        MemoryManager().getInstance()?.putUser(response.body()?.success?.user)

                        callback?.loadingSuccessful(response.body()?.success?.message) } else {
                        callback?.loadingFailed(response.body()?.error?.message) }
                    }else{
                        callback?.loadingFailed("Network problem....")
                    }
                }catch (e : Exception){
                    callback?.loadingFailed("Network problem....")

                }

            }

        })



    }
}