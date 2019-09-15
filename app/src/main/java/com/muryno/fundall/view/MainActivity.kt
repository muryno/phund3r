package com.muryno.fundall.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.muryno.fundall.R
import com.muryno.fundall.model.base.BaseActivity
import com.muryno.fundall.model.db.MemoryManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.profileImage
import kotlinx.android.synthetic.main.activity_profile.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profileImage.setOnClickListener {
            startActivity(Intent(applicationContext,MainActivity::class.java))

        }

        logout.setOnClickListener {
            MemoryManager().getInstance()?.signOut()
        }

        val user = MemoryManager().getUser()
        val mDefaultBackground = ContextCompat.getDrawable(applicationContext, R.drawable.profile)
        Glide.with(applicationContext)
            .load(user?.avatar)
            .centerCrop()
            .error(mDefaultBackground).into(profileImage)

    }
}
