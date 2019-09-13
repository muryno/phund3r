package com.muryno.fundall.utils

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.Build
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.muryno.fundall.utils.Debug.Companion.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

private val SIMPLE_DATE_FORMAT = SimpleDateFormat("dd_mm_yyyy_hh_mm_ss", Locale.ENGLISH)

@TargetApi(Build.VERSION_CODES.M)
fun isOnline(): Boolean {

    if (MainApplication.getInstance().applicationContext == null)
        return false

    val cm = MainApplication.getInstance().applicationContext
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    //        assert cm != null;
    //        return cm.getActiveNetworkInfo() != null &&
    //                cm.getActiveNetworkInfo().isConnectedOrConnecting();

    val activeNetwork = cm.activeNetwork
    return activeNetwork != null
}

 fun isValidEmail(email: String): Boolean {
    return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}


@Throws(IOException::class)
fun getCompressedFile(path: String, context: Context?): File? {
    if (context == null)
        return File(path)
    Log(path)
    val fresh = File(path)
    val size = fresh.length() / 1000 //To KB

    //doesn't need compression. Use as it is.
    if (abs(size) <= 400) {
        return fresh
    }


    var cacheDIR = context.externalCacheDir
    if (cacheDIR == null)
        cacheDIR = context.cacheDir
    val tempCacheDIR = cacheDIR!!.absolutePath + "/fintech/.Temp"
    val tempCacheFile = File(tempCacheDIR)
    if (!tempCacheFile.exists())
        tempCacheFile.mkdirs()

    val bitmap = BitmapUtils.decodeImageFromFiles(path)
    val mainFile = File(tempCacheFile, SIMPLE_DATE_FORMAT.format(Date()) + Random().nextInt(999999) + ".jpg")
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 75, outputStream)

    val fileOutputStream = FileOutputStream(mainFile)
    fileOutputStream.write(outputStream.toByteArray())
    fileOutputStream.flush()

    fileOutputStream.close()
    return mainFile
}


fun showKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = activity.currentFocus
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
