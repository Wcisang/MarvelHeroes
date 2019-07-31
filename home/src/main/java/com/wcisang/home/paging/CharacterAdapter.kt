package com.wcisang.home.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wcisang.core.domain.model.Character
import com.wcisang.core.state.Resource
import com.wcisang.home.R
import com.wcisang.home.databinding.CharacterListItemBinding

class CharacterAdapter(
    private val pagingState: MutableLiveData<Resource<Nothing>>,
    private val listener: (Character) -> Unit
) : PagedListAdapter<Character, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == R.layout.character_list_loading) {
            LoadingHolder(inflater.inflate(R.layout.character_list_loading, parent, false))
        } else {
            val itemBinding = CharacterListItemBinding.inflate(inflater)
            CharacterHolder(itemBinding)
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

    class CharacterHolder(private val binding: com.wcisang.home.databinding.CharacterListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(character: Character, listener: (Character) -> Unit) {
            binding.character = character
            itemView.setOnClickListener { listener(character) }
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
            R.layout.character_list_loading
        } else {
            R.layout.character_list_item
        }
    }

    private fun hasExtraRow() =
        pagingState.value != null && pagingState.value?.status != Resource.Status.SUCCESS

    fun changeLoadingStatus() {
        pagingState.value?.let {
            when(it.status) {
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