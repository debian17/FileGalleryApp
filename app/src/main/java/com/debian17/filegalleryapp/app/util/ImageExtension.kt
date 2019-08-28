package com.debian17.filegalleryapp.app.util

object ImageExtension {

    const val JPG = "jpg"

    const val PNG = "png"

    const val BMP = "bmp"

    const val GIF = "gif"

    fun getExtensions(): List<String> {
        return listOf(JPG, PNG, BMP, GIF)
    }

}