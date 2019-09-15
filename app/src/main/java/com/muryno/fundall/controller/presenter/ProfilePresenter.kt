package com.muryno.fundall.controller.presenter



import com.muryno.fundall.controller.view.profileView
import com.muryno.fundall.model.db.BaseData
import com.muryno.fundall.model.db.MemoryManager
import com.muryno.fundall.model.db.entity.Base
import com.muryno.fundall.model.db.entity.Infor
import com.muryno.fundall.model.server.RetrofitClient
import com.muryno.fundall.utils.Debug
import com.muryno.fundall.utils.ImageCompressionTask
import com.muryno.fundall.utils.MainApplication
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.io.File
import java.util.ArrayList

class ProfilePresenter( var callback : profileView) :
    com.muryno.fundall.controller.IImageCompressionTaskListener {


    private val REQUEST_SELECT_PHOTO = 200

    /** for compressing image size**/

    fun processImage(path: String) {
        val strings = ArrayList<String>()
        strings.add(path)
        MainApplication.getExecutorService()
            .execute(ImageCompressionTask(MainApplication.getInstance(), this, strings, REQUEST_SELECT_PHOTO))
    }


    override fun onCompressed(file: List<File>, id: Int) {
        if (file.isNullOrEmpty())
            return

        /** call picture upload**/
        postImage(file[0])
    }

    override fun onError(t: Throwable) {
        Debug.Log(t.message!!)
    }
    /** post  image to server**/

 private fun  postImage(photo : File?) {
     if (photo?.exists()!!) {

         callback.loadingStart()
         val reqFil = RequestBody.create(MediaType.parse("file/*"), photo)
         val fil = MultipartBody.Part.createFormData("avatar", photo.name, reqFil)


         RetrofitClient().getApi().profilePicUpdate(fil).enqueue(object : Callback<BaseData<Infor>> {
             override fun onFailure(call: Call<BaseData<Infor>>, t: Throwable) {
                 Debug.Log(t.message!!)
                 callback?.loadingFailed("Connection failed.. please try again!!")
             }

             override fun onResponse(
                 call: Call<BaseData<Infor>>,
                 response: Response<BaseData<Infor>>
             ) {

                 try {

                     if (response.body() != null && response.body()?.error == null) {


                         getUser()

                         callback?.loadingSuccessful(response.body()?.success?.message)

                     } else {
                         callback?.loadingFailed(response.body()?.error?.message)

                     }
                 }catch (e : Exception){
                     callback?.loadingFailed("Network problem....")

                 }


             }

         })
     }
 }


     fun getUser(){

         RetrofitClient().getApi().getUser().enqueue(object :Callback<BaseData<Infor>>{
             override fun onFailure(call: Call<BaseData<Infor>>, t: Throwable) {
                 Debug.Log(t.message!!)
                 callback?.loadingFailed("Connection failed.. please try again!!")
             }

             override fun onResponse(
                 call: Call<BaseData<Infor>>,
                 response: Response<BaseData<Infor>>
             ) {
                 try {

                     if (response.body() != null && response.body()?.error == null) {
                         MemoryManager().getInstance()?.putUser(response.body()?.success?.data)
                         callback?.loadingSuccessful(response.body()?.success?.message)
                     } else {
                         callback?.loadingFailed(response.body()?.error?.message)

                     }
                 }catch (e : Exception){
                     callback?.loadingFailed("Network problem....")

                 }
             }

         })
     }



 }


