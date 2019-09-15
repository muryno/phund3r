package com.muryno.fundall

import java.io.File

interface IImageCompressionTaskListener {
    fun onCompressed(file: List<File>, id: Int)
    fun onError(throwable: Throwable)
}