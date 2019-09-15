package com.muryno.fundall.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePicker
import com.muryno.fundall.R
import com.muryno.fundall.controller.presenter.ProfilePresenter
import com.muryno.fundall.controller.view.profileView
import com.muryno.fundall.model.base.BaseActivity
import com.muryno.fundall.model.db.MemoryManager
import com.muryno.fundall.utils.CustomImagePicker
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.File


class ProfileActivity : BaseActivity(), profileView {




    /** controller**/

    var presenter : ProfilePresenter? =null
    private var photo: File?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        presenter = ProfilePresenter(this)

        fab_camera.setOnClickListener{
            //function in base activity
            imagePicker("Profile Image")

        }

        gohome.setOnClickListener {
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }
        updateUI()


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            val path = data?.let { CustomImagePicker.getImage(it) }
            if (path != null)
                presenter?.processImage(path)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }


    /** update profile information**/

    fun updateUI(){
        val user = MemoryManager().getUser()


        val mDefaultBackground = ContextCompat.getDrawable(applicationContext, R.drawable.profile)
        Glide.with(applicationContext)
            .load(user?.avatar)
            .centerCrop()
            .error(mDefaultBackground).into(profileImage)

        fr_name.text = user?.firstname
        ls_name.text = user?.lastname
        e_email.text = user?.email
        target.text = user?.monthly_target

    }

    override fun loadingStart() {
        llProgressBar.visibility = View.VISIBLE
    }

    override fun loadingFailed(msg: String?) {
        llProgressBar.visibility = View.GONE
        msg?.let { error(it) }


    }

    override fun loadingSuccessful(msg: String?) {
        msg?.let { toastSuccess(it) }

        updateUI()

        llProgressBar.visibility = View.GONE

    }
}
