package com.muryno.fundall.model.db.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class mainUi(imagee: String?) {

    var image: String?= imagee

    fun getImages() : String?{
        return image
    }
}
