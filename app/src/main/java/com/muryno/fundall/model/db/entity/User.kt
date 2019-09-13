package com.muryno.fundall.model.db.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
class User : Serializable {


    @SerializedName("id")
    @Expose
    var id: Int?=null



    @SerializedName("firstname")
    @Expose
    var firstname: String? = null


    @SerializedName("lastname")
    @Expose
    var lastname: String? = null



    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("expires_at")
    @Expose
    var expires_at: String? = null

    @SerializedName("token_type")
    @Expose
    var token_type: String? = null

    @SerializedName("access_token")
    @Expose
    var authorization: String? = null

    @SerializedName("created_at")
    @Expose
    var created_at: String? = null

    @SerializedName("monthly_target")
    @Expose
    var monthly_target: String? = null


    @SerializedName("avatar")
    @Expose
    var avatar: String? = null
}