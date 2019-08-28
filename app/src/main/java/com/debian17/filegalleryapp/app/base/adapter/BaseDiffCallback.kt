package com.debian17.filegalleryapp.app.base.adapter

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffCallback<T>(
    protected val oldItems: List<T>,
    protected val newItems: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

}