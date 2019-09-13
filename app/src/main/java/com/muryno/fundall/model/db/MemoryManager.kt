package com.muryno.community.db

import android.content.Intent
import android.content.SharedPreferences
import com.google.gson.Gson
import com.muryno.fundall.model.db.entity.User
import com.muryno.fundall.utils.MainApplication
import com.muryno.fundall.view.LoginActivity

class MemoryManager() {


    private var sInstance:MemoryManager?=null
    private  var mSharedPreferences: SharedPreferences
    private var editor: SharedPreferences.Editor? = null
    private val PREF_NAME = "fintech_user_app"
    private val PREF_MODE = 0
    private val KEY_USER = "user"


    init {
        mSharedPreferences = MainApplication.getInstance().applicationContext
            .getSharedPreferences(PREF_NAME, PREF_MODE)
        editor= mSharedPreferences.edit()
    }




    @Synchronized
    fun getInstance(): MemoryManager? {
        if (sInstance == null) sInstance = MemoryManager()
        return sInstance
    }

    fun putUser(user: User?){
        val gson = Gson()
        val json = gson.toJson(user)
        editor?.putString(KEY_USER, json)
        editor?.commit()
    }

    fun getUser(): User?{

        if (mSharedPreferences.getString(KEY_USER, null) != null) {
            val gson = Gson()
            val json = mSharedPreferences.getString(KEY_USER, null)
            return gson.fromJson(json, User::class.java)
        }
        return null
    }

    fun signOut() {
        editor?.clear()?.apply()
        val intent = Intent(MainApplication.getInstance().applicationContext, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        MainApplication.getInstance().startActivity(intent)
    }
}