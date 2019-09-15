package com.muryno.fundall.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muryno.fundall.R
import com.muryno.fundall.model.base.BaseActivity
import com.muryno.fundall.model.db.MemoryManager
import kotlinx.android.synthetic.main.activity_login_step.*

class LoginActivityStep : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_step)



        if(MemoryManager().getInstance()?.getUser()!=null){
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }


        lay_3.setOnClickListener {
            startActivity(Intent(applicationContext,RegistrationActivity::class.java))
            finish()

        }

        login.setOnClickListener {
            startActivity(Intent(applicationContext,LoginActivity::class.java))
            finish()



        }

        biometric.setOnClickListener {
            toastWarning("Oga chill .. we yet to implement biometric...you can try password login")
        }
    }
}
