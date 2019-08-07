package com.wcisang.home.paging

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedListAdapter
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.wcisang.core.domain.model.Character
import com.wcisang.core.state.Resource
import com.wcisang.core.utils.ImageUtils
import kotlinx.android.synthetic.main.character_list_item.view.*


class CharacterAdapter(
    private val pagingState: MutableLiveData<Resource<Nothing>>,
    private val listener: (Character) -> Unit
) : PagedListAdapter<Character, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == com.wcisang.home.R.layout.character_list_loading) {
            LoadingHolder(
                inflater.inflate(
                    com.wcisang.home.R.layout.character_list_loading,
                    parent,
                    false
                )
            )
        } else {
            CharacterHolder(
                inflater.inflate(
                    com.wcisang.home.R.layout.character_list_item,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterHolder -> {
                val character = getItem(position)
                holder.bindView(character!!, listener)
            }
            else -> {
                (holder as LoadingHolder).bindView()
            }
        }
    }

    class CharacterHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(character: Character, listener: (Character) -> Unit) = with(itemView) {
            itemView.setOnClickListener { listener(character) }
            tvCharacterName.text = character.name
            ivCharBackground.background = null
            Picasso.get().cancelRequest(ivCharacterThumb)
            Picasso.get().load(
                ImageUtils.formattMarvelImage(
                    character.thumbnail.path,
                    ImageUtils.ImageType.MEDIUM, character.thumbnail.extension
                )
            ).fit().into(ivCharacterThumb,
                object : Callback {
                    override fun onSuccess() {
                        val bitmap = (ivCharacterThumb.drawable as BitmapDrawable).bitmap
                        Palette.from(bitmap).generate {
                            val light = it?.getDarkVibrantColor(Color.RED) ?: Color.RED
                            val dark = it?.getDarkMutedColor(Color.BLACK) ?: Color.BLACK
                            ImageUtils.setGradienteBackground(ivCharBackground, light, dark)
                        }
                    }

                    override fun onError(e: Exception?) {
                    }

                })

        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        with(holder.itemView) {
            ivCharBackground?.background = null
        }
    }

    class LoadingHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView() {

        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            com.wcisang.home.R.layout.character_list_loading
        } else {
            com.wcisang.home.R.layout.character_list_item
        }
    }

    private fun hasExtraRow() =
        pagingState.value != null && pagingState.value?.status != Resource.Status.SUCCESS

    fun changeLoadingStatus() {
        pagingState.value?.let {
            when (it.status) {
                Resource.Status.LOADING -> {
                    notifyItemInserted(itemCount + 1)
                }
                Resource.Status.SUCCESS -> {
                    notifyItemRemoved(itemCount)
                }
                Resource.Status.ERROR -> {

                }
            }
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