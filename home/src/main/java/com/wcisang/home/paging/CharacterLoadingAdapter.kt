package com.wcisang.home.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wcisang.home.R
import kotlinx.android.synthetic.main.character_list_loading.view.*

class CharacterLoadingAdapter(private val retry: () -> Unit) : LoadStateAdapter<CharacterLoadingAdapter.LoadStatusViewHolder>() {

    override fun onBindViewHolder(holder: LoadStatusViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStatusViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_list_loading, parent, false)
        return LoadStatusViewHolder(view, retry)
    }

    class LoadStatusViewHolder(
        item: View,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(item) {

        fun bind(loadState : LoadState) {
//            itemView.btRetry.setOnClickListener { retry.invoke() }
//            if (loadState is LoadState.Error) {
//                itemView.tvError.text = loadState.error.localizedMessage
//            }
            itemView.pbLoading.isVisible = loadState is LoadState.Loading
//            itemView.btRetry.isVisible = loadState !is LoadState.Loading
//            itemView.tvError.isVisible = loadState !is LoadState.Loading
        }
    }
}