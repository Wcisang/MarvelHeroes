package com.wcisang.widget.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.wcisang.core.domain.model.Character
import com.wcisang.core.state.Resource
import com.wcisang.navigator.Action
import com.wcisang.widget.R
import com.wcisang.widget.usecase.GetCharactersWidgetUseCase
import kotlinx.coroutines.runBlocking


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
                Log.i("WILL_TESTE", list.toString())
            } else {
                Log.i("WILL_TESTE", "ERRO ${result.messageError}")
            }
        }
    }

    override fun hasStableIds() = true

    override fun getViewAt(position: Int): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.character_widget_item)

//        val posterUrl = ImageUtility
//            .getImageUrl(mMovies.get(position).getPosterPath())
//        Log.v(LOG_TAG, "posterUrl = $posterUrl")

        val character = list[position]
        views.setTextViewText(R.id.tvCharacterName, character.name)

//        try {
//            val poster = Picasso.with(this@StackWidgetRemoteViewsService)
//                .load(posterUrl)
//                .get()
//            views.setImageViewBitmap(com.wcisang.widget.R.id.widget_movie_poster, poster)
//        } catch (e: IOException) {
//            Log.e(LOG_TAG, "ERROR: ", e)
//        }


        val fillInIntent = Intent()
        fillInIntent.putExtra(Action.KEY_CHARACTER, character)
        views.setOnClickFillInIntent(R.id.containerCharacter, fillInIntent)
        return views
    }

    override fun getCount(): Int {
        Log.i("WILL_TESTE", list.size.toString())
        return list.size
    }

    override fun getViewTypeCount() = 1

    override fun onDestroy() {
    }
}
