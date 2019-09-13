package com.muryno.fundall.controller.view


interface LoginView : BaseView {
    fun invalidEmail(msg: String)

    fun invalidPass(msg: String)
}
