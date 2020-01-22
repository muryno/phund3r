package com.muryno.fundall.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muryno.fundall.R
import com.muryno.fundall.model.db.entity.mainUi
import com.muryno.fundall.utils.MainApplication
import kotlinx.android.synthetic.main.layout_mainui.view.*
import java.util.*

class mainUiAdapter(var studentDataList: ArrayList<mainUi>?) :
    RecyclerView.Adapter<mainUiAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_mainui, viewGroup, false)
        return MyViewHolder(itemView)
    }



    override fun getItemCount(): Int {
        return studentDataList?.size ?: 0
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){

        val img_view : ImageView = itemView.img_view

    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tri = studentDataList?.get(position)
        Glide.with(MainApplication.getInstance())
            .load(tri?.getImages())
            .into(holder.img_view)
    }

}