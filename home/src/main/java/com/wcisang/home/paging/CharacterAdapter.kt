package com.wcisang.home.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wcisang.core.domain.model.Character
import com.wcisang.customviews.utils.ImageUtils
import com.wcisang.customviews.views.CharacterListItem
import com.wcisang.home.R
import kotlinx.android.synthetic.main.character_list_item.view.*


class CharacterAdapter(
    private val listener: (Character) -> Unit
) : PagingDataAdapter<Character, CharacterAdapter.CharacterHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterHolder {
        return CharacterHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.character_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = getItem(position)
        character?.let { holder.bindView(it, listener) }
    }

    class CharacterHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(character: Character, listener: (Character) -> Unit) = with(itemView) {
            val model = character.mapToListModel()
            findViewById<CharacterListItem>(R.id.view_character).set(model)
            setOnClickListener { listener(character) }
        }

        private fun Character.mapToListModel(): CharacterListItem.ListItemModel {
            return CharacterListItem.ListItemModel(
                name = name,
                image = ImageUtils.formatMarvelImage(
                    thumbnail.path,
                    ImageUtils.ImageType.MEDIUM, thumbnail.extension
                )
            )
        }
    }

    override fun onViewRecycled(holder: CharacterHolder) {
        with(holder.itemView) {
            view_character?.recycleBackground()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(
                oldConcert: Character,
                newConcert: Character
            ) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(
                oldConcert: Character,
                newConcert: Character
            ) = oldConcert == newConcert
        }
    }
}