package com.debian17.filegalleryapp.app.util

import android.os.Environment

object FileUtils {

    fun isExternalStorageMounted(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

}