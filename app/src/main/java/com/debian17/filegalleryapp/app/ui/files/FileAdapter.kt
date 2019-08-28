package com.debian17.filegalleryapp.app.ui.files

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.debian17.filegalleryapp.R
import com.debian17.filegalleryapp.app.base.adapter.BaseAdapter
import com.debian17.filegalleryapp.app.util.ImageExtension
import java.io.File

class FileAdapter(
    context: Context,
    private val fileClickListener: FileCLickListener
) : BaseAdapter<File, FileAdapter.FileViewHolder>() {

    interface FileCLickListener {
        fun onFileClick(file: File)
    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val icFolder = ContextCompat.getDrawable(context, R.drawable.ic_folder)
    private val icFile = ContextCompat.getDrawable(context, R.drawable.ic_file)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val view = inflater.inflate(R.layout.item_file, parent, false)
        val holder = FileViewHolder(view)
        holder.itemView.setOnClickListener {
            val item = getItems()[holder.adapterPosition]
            fileClickListener.onFileClick(item)
        }
        return holder
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val item = getItems()[position]
        if (item.isDirectory) {
            holder.ivFileLogo.setImageDrawable(icFolder)
            holder.tvName.text = item.name
        } else {
            val extension = item.extension
            if (extension in ImageExtension.getExtensions()) {
                Glide.with(holder.ivFileLogo)
                    .load(item)
                    .into(holder.ivFileLogo)
            } else {
                holder.ivFileLogo.setImageDrawable(icFile)
            }
            holder.tvName.text = item.name
        }
    }

    class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivFileLogo: ImageView = itemView.findViewById(R.id.ivFileLogo)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
    }

}