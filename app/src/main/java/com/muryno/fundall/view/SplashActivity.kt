package com.muryno.fundall.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muryno.fundall.R
import com.muryno.fundall.model.db.MemoryManager
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Timer().schedule(1000){

            startActivity(Intent(applicationContext, LoginActivityStep::class.java))
            finish()

        }
    }
}
