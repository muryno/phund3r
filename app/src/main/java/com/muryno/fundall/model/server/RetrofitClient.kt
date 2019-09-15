package com.muryno.fundall.model.server

import com.muryno.fundall.model.db.MemoryManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient{

    val BASE_URL = "https://test.fundall.io/"

    var mInstance: RetrofitClient? = null

    var mRetrofit: Retrofit? = null

    private var authorization: String?=null

    init {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        authorization = if (MemoryManager().getUser() != null) MemoryManager().getUser()?.authorization else ""

        val Bearer = "Bearer "
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "$Bearer  $authorization" )
                    .build()
            chain.proceed(newRequest)
        }.addInterceptor(interceptor).build()

        @Synchronized
        fun getInstance(): RetrofitClient {
            if (mInstance == null) {
                mInstance = RetrofitClient()
            }
            return mInstance!!
        }
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
        fun reset() {
            mInstance = null
            getInstance()
        }
    }
    fun getApi(): ApiInterface {
        return mRetrofit!!.create(ApiInterface::class.java)
    }



}