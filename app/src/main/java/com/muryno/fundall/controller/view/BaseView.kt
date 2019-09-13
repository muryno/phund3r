package com.muryno.fundall.controller.view


interface BaseView {

    fun loadingStart()

    fun loadingFailed(msg: String?)

    fun loadingSuccessful(msg: String?)
}
