package com.debian17.filegalleryapp.app.ui.files.image

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.debian17.filegalleryapp.R
import com.debian17.filegalleryapp.app.base.adapter.BaseAdapter
import java.io.File

class ImageAdapter(context: Context) : BaseAdapter<File, ImageAdapter.ImageViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        Log.e("ADAPTER", "onCreateViewHolder")
        val view = inflater.inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Log.e("ADAPTER", "onBindViewHolder")
        val file = getItems()[position]
        Glide.with(holder.ivImage)
            .load(file)
            .into(holder.ivImage)
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView = itemView.findViewById(R.id.ivImage)
    }

}