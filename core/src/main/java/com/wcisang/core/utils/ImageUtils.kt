package com.wcisang.core.utils

import android.graphics.drawable.GradientDrawable
import android.view.View


object ImageUtils {

    enum class ImageType(val typeName: String){
        SMALL("small"),
        MEDIUM("medium"),
        XLARGE("xlarge"),
        FANTASTIC("fantastic"),
        UNCANNY("uncanny"),
        INCREDIBLE("incredible")
    }

    fun formattMarvelImage(url: String, imageType: ImageType, extension: String) : String{
        return "$url/portrait_${imageType.typeName}.$extension"
    }

    fun setGradienteBackground(v: View, light: Int, dark: Int) {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.orientation = GradientDrawable.Orientation.TR_BL
        shape.colors = intArrayOf(light, dark)
        v.background = shape
    }
}