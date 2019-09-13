package com.muryno.fundall.model.db

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseData<T> {

    @SerializedName("message")
    @Expose
     var message = ""

    @SerializedName("data")
    @Expose
     var data: T? = null


}