package com.muryno.fintech.ui.presenter


import com.muryno.community.ui.interfaces.IImageCompressionTaskListener

import com.muryno.fundall.controller.view.profileView
import com.muryno.fundall.utils.Debug
import com.muryno.fundall.utils.ImageCompressionTask
import com.muryno.fundall.utils.MainApplication
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

import java.io.File
import java.util.ArrayList

class ProfilePresenter( var callback : profileView) : IImageCompressionTaskListener,
    com.muryno.fundall.controller.IImageCompressionTaskListener {


    private val REQUEST_SELECT_PHOTO = 200

    fun processImage(path: String) {
        val strings = ArrayList<String>()
        strings.add(path)
        MainApplication.getExecutorService()
            .execute(ImageCompressionTask(MainApplication.getInstance(), this, strings, REQUEST_SELECT_PHOTO))
    }


    override fun onCompressed(file: List<File>, id: Int) {
        if (file.isNullOrEmpty())
            return

        postImage(file[0])
    }

    override fun onError(t: Throwable) {
        Debug.Log(t.message!!)
    }

 private fun  postImage(photo : File?){
     if(photo?.exists()!! ){

         callback.loadingStart()
         val reqFil = RequestBody.create(MediaType.parse("file/*"), photo)
         val fil = MultipartBody.Part.createFormData("image", photo.name, reqFil)

     }



 }


}