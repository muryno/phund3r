package com.muryno.fundall.model.db.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Infor {


    @SerializedName("message")
    @Expose
    var message: String?=null

    @SerializedName("status")
    @Expose
    var status: String? = null


    @SerializedName("user")
    @Expose
    var user: User? = null

}
