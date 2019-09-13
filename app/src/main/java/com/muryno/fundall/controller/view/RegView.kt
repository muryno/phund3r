package com.muryno.fundall.controller.view

interface RegView: BaseView {
    fun fnameError(msg: String)

    fun lnameError(msg: String)

    fun emailError(msg: String)

    fun passError(msg: String)

    fun comfpassError(msg : String)
}