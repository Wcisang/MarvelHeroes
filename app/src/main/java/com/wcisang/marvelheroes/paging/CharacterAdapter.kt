package com.wcisang.marvelheroes.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wcisang.core.domain.model.Character
import com.wcisang.marvelheroes.databinding.CharacterListItemBinding

class CharacterAdapter(private val listener: (Character) -> Unit) : PagedListAdapter<Character, CharacterAdapter.CharacterHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val itemBinding = CharacterListItemBinding.inflate(LayoutInflater.from(parent.context))
        return CharacterHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = getItem(position)
        character?.let {
            holder.bindView(character, listener)
        }
    }

    class CharacterHolder(private val binding: CharacterListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(character: Character, listener: (Character)->Unit) {
            binding.character = character
            itemView.setOnClickListener { listener(character) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldConcert: Character,
                                         newConcert: Character) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(oldConcert: Character,
                                            newConcert: Character) = oldConcert == newConcert
        }
    }
}