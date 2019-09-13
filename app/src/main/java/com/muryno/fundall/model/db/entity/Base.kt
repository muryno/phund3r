package com.muryno.fundall.model.db.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Base : Serializable {


    @SerializedName("error")
    @Expose
    var error: Infor? = null


    @SerializedName("success")
    @Expose
    var success: Infor? = null

}