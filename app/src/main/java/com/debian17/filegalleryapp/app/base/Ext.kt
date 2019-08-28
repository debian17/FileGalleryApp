package com.debian17.filegalleryapp.app.base

import android.view.View
import android.widget.Toast
import com.debian17.filegalleryapp.app.base.ui.BaseFragment
import com.google.android.material.snackbar.Snackbar
import java.io.File

fun BaseFragment.longToast(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun BaseFragment.shortToast(text: Int) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun View.shortSnackbar(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_SHORT).show()
}

fun View.longSnackbar(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()
}

fun File.sortedFiles(): List<File> {
    return if (this.isDirectory) {
        val all = this.listFiles().toList()
        all.sortedWith(compareBy(File::isFile, File::getName))
    } else {
        emptyList()
    }
}