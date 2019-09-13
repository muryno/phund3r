package com.muryno.fundall.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muryno.fundall.R
import kotlinx.android.synthetic.main.activity_login_step.*

class LoginActivityStep : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_step)





        lay_3.setOnClickListener {
            startActivity(Intent(applicationContext,RegistrationActivity::class.java))

        }

        login.setOnClickListener {
            startActivity(Intent(applicationContext,LoginActivity::class.java))

        }
    }
}
