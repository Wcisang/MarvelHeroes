package com.wcisang.core.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.palette.graphics.Palette


object ImageUtils {

    enum class ImageType(val typeName: String) {
        SMALL("small"),
        MEDIUM("medium"),
        XLARGE("xlarge"),
        FANTASTIC("fantastic"),
        UNCANNY("uncanny"),
        INCREDIBLE("incredible")
    }

    fun formattMarvelImage(url: String, imageType: ImageType, extension: String): String {
        return "$url/portrait_${imageType.typeName}.$extension"
    }

    fun setGradientBackground(v: View, light: Int, dark: Int) {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.orientation = GradientDrawable.Orientation.TR_BL
        shape.colors = intArrayOf(light, dark)
        v.background = shape
    }

    fun setImageViewBackgroundFromBitmap(v: View, bitmap: Bitmap) {
        Palette.from(bitmap).generate {
            val light = it?.getDarkVibrantColor(Color.RED)
                ?: it?.getDominantColor(Color.RED)
                ?: Color.RED
            val dark = it?.getDarkMutedColor(Color.BLACK)
                ?: it?.getDominantColor(Color.RED)
                ?: Color.RED
            setGradientBackground(v, light, dark)
        }
    }


}