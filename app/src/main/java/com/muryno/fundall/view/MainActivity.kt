package com.muryno.fundall.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.muryno.fundall.R
import com.muryno.fundall.model.base.BaseActivity
import com.muryno.fundall.model.db.MemoryManager
import com.muryno.fundall.utils.BottomSheetLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_recycler_item.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profileImage.setOnClickListener {
            startActivity(Intent(applicationContext,ProfileActivity::class.java))

        }

        logout.setOnClickListener {
            signout()
        }

        val user = MemoryManager().getUser()
        val mDefaultBackground = ContextCompat.getDrawable(applicationContext, R.drawable.profile)
        Glide.with(applicationContext)
            .load(user?.avatar)
            .centerCrop()
            .error(mDefaultBackground).into(profileImage)


        card_1.setOnClickListener {
            toastSuccess("still under development mode")
        }

        card_2.setOnClickListener {
            toastSuccess("keep calm!!")
        }

        val layout = findViewById<View>(R.id.bottom_sheet_layout) as BottomSheetLayout

        layout.toggle()
        layout.collapse()
        layout.isExpended()
        layout.setCornerRadius(28f)

    }

    fun signout(){
        val builder = AlertDialog.Builder(this@MainActivity)

        // Set the alert dialog title
        builder.setTitle("Sign out")

        builder.setMessage("Are you sure you want to sign out?")

        builder.setPositiveButton("YES"){dialog, which ->
            MemoryManager().getInstance()?.signOut()

        }
        builder.setNegativeButton("No"){dialog,which ->dialog.dismiss()
        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }




}
