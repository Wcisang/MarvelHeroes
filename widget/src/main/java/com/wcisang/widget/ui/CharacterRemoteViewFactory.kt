package com.wcisang.widget.ui

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.squareup.picasso.Picasso
import com.wcisang.core.domain.model.Character
import com.wcisang.core.state.Resource
import com.wcisang.customviews.utils.ImageUtils
import com.wcisang.navigator.Action
import com.wcisang.widget.R
import com.wcisang.widget.usecase.GetCharactersWidgetUseCase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CharacterRemoteViewFactory(
    private val context: Context,
    private val getCharactersWidgetUseCase: GetCharactersWidgetUseCase
) : RemoteViewsService.RemoteViewsFactory {

    private var list = mutableListOf<Character>()

    override fun onCreate() {
    }

    override fun getLoadingView(): RemoteViews {
        return RemoteViews(context.packageName, R.layout.character_widget_item)
    }

    override fun getItemId(position: Int): Long {
        if (position < list.size) {
            return list[position].id.toLong()
        }
        return position.toLong()
    }

    override fun onDataSetChanged() {
        runBlocking {
            val result = getCharactersWidgetUseCase.execute(
                GetCharactersWidgetUseCase
                    .Params
                    .forCharacter(3, 1)
            )
            if (result.status == Resource.Status.SUCCESS) {
                list.clear()
                list.addAll(result.data?.toList()!!)
            }
        }
    }

    override fun hasStableIds() = true

    override fun getViewAt(position: Int): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.character_widget_item)

        val character = list[position]
        views.setTextViewText(R.id.tvCharacterName, character.name)

        val imageUrl = ImageUtils.formatMarvelImage(
            character.thumbnail.path,
            ImageUtils.ImageType.MEDIUM, character.thumbnail.extension
        )

        runCatching {
            val poster = Picasso.get()
                .load(imageUrl)
                .get()
            views.setImageViewBitmap(R.id.ivCharacter, poster)
        }

        val fillInIntent = Intent()
        fillInIntent.putExtra(Action.KEY_CHARACTER, character)
        views.setOnClickFillInIntent(R.id.containerCharacter, fillInIntent)
        return views
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getViewTypeCount() = 1

    override fun onDestroy() {}
}
