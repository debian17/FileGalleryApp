package com.debian17.filegalleryapp.app.ui.files

import com.debian17.filegalleryapp.app.base.adapter.BaseDiffCallback
import java.io.File

class FileDiffCallback(
    oldItems: List<File>,
    newItems: List<File>
) : BaseDiffCallback<File>(oldItems, newItems) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.name == newItem.name
    }

}