package com.muryno.fundall.utils

private val TAG = "fundall"

class Debug() {
    companion object {
        fun Log(data: String, throwable: Throwable) {
            Log(data + "_", throwable)
        }

        fun Log(throwable: Throwable) {
            Log("ERROR", throwable)
        }

        fun Log(data: String) {
            android.util.Log.d(TAG, data + "_")
        }
    }
}
