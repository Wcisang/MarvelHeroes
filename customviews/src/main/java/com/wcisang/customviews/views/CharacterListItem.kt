package com.wcisang.customviews.views

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.palette.graphics.Palette
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.wcisang.customviews.R
import com.wcisang.customviews.utils.ImageUtils
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterListItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context,
            R.layout.character_item, this)
    }

    fun set(model: ListItemModel) {
        tvCharacterName.text = model.name
        recycleBackground()
        Picasso.get().cancelRequest(ivCharacterThumb)
        Picasso.get().load(
            model.image
        )
            .fit().into(ivCharacterThumb,
            object : Callback {
                override fun onSuccess() {
                    val bitmap = (ivCharacterThumb.drawable as BitmapDrawable).bitmap
                    Palette.from(bitmap).generate {
                        val light = it?.getDarkVibrantColor(Color.RED) ?: Color.RED
                        val dark = it?.getDarkMutedColor(Color.BLACK) ?: Color.BLACK
                        ImageUtils.setGradientBackground(ivCharBackground, light, dark)
                    }
                }

                override fun onError(e: Exception?) {
                }

            })
    }

    fun recycleBackground() {
        ivCharBackground.background = null
    }

    data class ListItemModel(val name: String, val image: String)

}