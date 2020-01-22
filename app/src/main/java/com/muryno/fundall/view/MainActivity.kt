package com.muryno.fundall.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muryno.fundall.R
import com.muryno.fundall.adapter.mainUiAdapter
import com.muryno.fundall.model.base.BaseActivity
import com.muryno.fundall.model.db.MemoryManager
import com.muryno.fundall.model.db.entity.mainUi
import com.muryno.fundall.utils.BottomSheetLayout
import com.muryno.fundall.utils.MainApplication
import com.muryno.fundall.utils.ScrollingLinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_recycler_item.*
import java.security.AccessController.getContext


class MainActivity : BaseActivity() {


    private  var adapter : mainUiAdapter? = null

    private var arrr : ArrayList<mainUi>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profileImage.setOnClickListener {
            startActivity(Intent(applicationContext,ProfileActivity::class.java))

        }

        logout.setOnClickListener {
            signout()
        }
        arrr   = ArrayList()


        val layout = findViewById<View>(R.id.bottom_sheet_layout) as BottomSheetLayout

        layout.toggle()
        layout.collapse()
        layout.isExpended()
        layout.setCornerRadius(28f)

        updateUi()
        adapter = mainUiAdapter(arrr)
        val manager: RecyclerView.LayoutManager =
            LinearLayoutManager(MainApplication.getInstance(), LinearLayoutManager.HORIZONTAL, false)
        recycler_view.layoutManager = manager
        recycler_view.isNestedScrollingEnabled = false


      val  scrollManager =
            ScrollingLinearLayoutManager(MainApplication.getInstance(), LinearLayoutManager.HORIZONTAL, false, 100)
        recycler_view.layoutManager = scrollManager
        recycler_view.adapter = adapter



    }

    private fun updateUi(){
        var arr :ArrayList<mainUi> = ArrayList()
        arr.add(mainUi("https://schoolbackend-bucket.s3.eu-west-2.amazonaws.com/pictures/fun.jpeg"))
        arr.add(mainUi("https://schoolbackend-bucket.s3.eu-west-2.amazonaws.com/pictures/fund.jpeg"))
        arr.add(mainUi("https://schoolbackend-bucket.s3.eu-west-2.amazonaws.com/pictures/logo.jpg"))
        arr.add(mainUi("https://schoolbackend-bucket.s3.eu-west-2.amazonaws.com/pictures/5e222359205d3cd4ea8bc669.jpeg"))
        arr.add(mainUi("https://schoolbackend-bucket.s3.eu-west-2.amazonaws.com/pictures/fun.jpeg"))
        arr.add(mainUi("https://schoolbackend-bucket.s3.eu-west-2.amazonaws.com/pictures/fund.jpeg"))

        arrr?.addAll(arr)

        adapter?.notifyDataSetChanged()
    }



    private fun signout(){
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
