package com.muryno.fundall.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muryno.fundall.R
import com.muryno.fundall.controller.presenter.RegisterPresenter
import com.muryno.fundall.controller.view.RegView
import com.muryno.fundall.model.base.BaseActivity
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : BaseActivity(), RegView {
    override fun fnameError(msg: String) {
        f_name.error  = msg
    }

    override fun lnameError(msg: String) {
        l_name.error  = msg
    }

    override fun emailError(msg: String) {
        e_mail.error = msg
    }

    override fun passError(msg: String) {
        password.error  = msg

    }

    override fun comfpassError(msg: String) {

        c_password.error  = msg
    }

    override fun loadingStart() {
        btn_reg.startLoading()
    }

    override fun loadingFailed(msg: String?) {
        btn_reg.loadingFailed()
        msg?.let { toastError(it) }
    }

    override fun loadingSuccessful(msg: String?) {
        btn_reg.loadingSuccessful()
        msg?.let { toastSuccess(it) }
        startActivity(Intent(applicationContext,LoginActivityStep::class.java))
        finish()
    }

    var presenter : RegisterPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        presenter= RegisterPresenter(this)


        btn_reg.setOnClickListener {
            presenter?.validate(f_name.text.toString(),l_name.text.toString(),e_mail.text.toString(),password.text.toString(),c_password.text.toString())
        }

        log_in.setOnClickListener { Intent(applicationContext,LoginActivityStep::class.java) }
    }
}
