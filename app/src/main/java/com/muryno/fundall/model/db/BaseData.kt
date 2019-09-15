package com.muryno.fundall.model.db

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.muryno.fundall.model.db.entity.Infor

class BaseData<T> {

    @SerializedName("message")
    @Expose
     var message = ""

    @SerializedName("data")
    @Expose
     var data: T? = null


    @SerializedName("error")
    @Expose
    var error: T? = null


    @SerializedName("success")
    @Expose
    var success: T? = null

}