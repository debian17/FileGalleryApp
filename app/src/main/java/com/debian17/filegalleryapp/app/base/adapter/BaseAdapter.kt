package com.debian17.filegalleryapp.app.base.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {

    private val items = ArrayList<T>()

    fun getItems(): List<T> {
        return items
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun replaceItems(items: List<T>, diffCallback: BaseDiffCallback<T>) {
        val result = DiffUtil.calculateDiff(diffCallback)

        this.items.clear()
        this.items.addAll(items)

        result.dispatchUpdatesTo(this)
    }

}