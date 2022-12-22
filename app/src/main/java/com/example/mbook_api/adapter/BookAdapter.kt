package com.example.mbook_api.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mbook_api.R
import com.example.mbook_api.activity.detail.DetailActivity
import com.example.mbook_api.activity.edit.BookEdit
import com.example.mbook_api.api.APIClient
import com.example.mbook_api.model.BookModel
import com.google.android.gms.common.api.Result
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class BookAdapter(private val dataList: ArrayList<BookModel>): RecyclerView.Adapter<BookAdapter.ViewHolderData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.ViewHolderData {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)
        return ViewHolderData(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        val item = dataList[position]
        holder.judul.text = item.judul
        holder.itemView.setOnClickListener {
            val ctx = holder.contex
            val intent = Intent(ctx,DetailActivity::class.java)
            intent.putExtra("ID", item.id.toString())
            intent.putExtra("JUDUL", item.judul.toString())
            intent.putExtra("ISI_KONTEN", item.isi_konten.toString())
            ctx.startActivity(intent)
        }
    }
    override fun getItemCount(): Int = dataList.size

    class ViewHolderData(itemView: View): RecyclerView.ViewHolder(itemView) {
        val judul : TextView = itemView.findViewById(R.id.tv_judul)
        val contex = itemView.context
    }


}