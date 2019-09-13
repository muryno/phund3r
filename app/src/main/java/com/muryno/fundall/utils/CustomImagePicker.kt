package com.muryno.fundall.utils

import android.app.Activity
import android.content.Intent
import com.esafirm.imagepicker.features.ImagePicker
import java.util.ArrayList

object  CustomImagePicker {

    fun shouldHandle(requestCode: Int, resultCode: Int, data: Intent?, userRequestCode: Int): Boolean {
        return (resultCode == Activity.RESULT_OK
                && requestCode == userRequestCode
                && data != null)
    }

    fun getImages(intent: Intent): List<String>? {
        val data = ImagePicker.getImages(intent)
        if (data != null) {
            if (!data.isEmpty()) {
                val list = ArrayList<String>()
                list.clear()
                for (image in data) {
                    list.add(image.path)
                }
                return list
            }
        }
        return null
    }

    fun getImage(intent: Intent): String? {
        val data = ImagePicker.getFirstImageOrNull(intent)
        return data?.path
    }
}