package com.muryno.fundall.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muryno.fundall.R
import com.muryno.fundall.controller.presenter.LoginPresenter
import com.muryno.fundall.controller.view.LoginView
import com.muryno.fundall.model.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {
    override fun invalidEmail(msg: String) {

        e_mail.error = msg
    }

    override fun invalidPass(msg: String) {

        l_password.error = msg
    }

    override fun loadingStart() {
        btn_login.startLoading()
    }

    override fun loadingFailed(msg: String?) {
        btn_login.loadingFailed()
        msg?.let { toastError(it) }
    }

    override fun loadingSuccessful(msg: String?) {
        btn_login.loadingSuccessful()
        msg?.let { toastSuccess(it) }
        startActivity(Intent(applicationContext,ProfileActivity::class.java))

        finish()
    }

    private var presenter: LoginPresenter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter= LoginPresenter(this)


        btn_login.setOnClickListener {
            presenter?.validate(e_mail.text.toString(),l_password.text.toString())

            startActivity(Intent(applicationContext,ProfileActivity::class.java))
            finish()
        }

        lay_2.setOnClickListener {
            startActivity(Intent(applicationContext,RegistrationActivity::class.java))
            finish()
        }
    }
}
