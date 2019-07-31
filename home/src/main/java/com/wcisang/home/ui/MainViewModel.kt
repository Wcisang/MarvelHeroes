package com.wcisang.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wcisang.core.domain.model.Character
import com.wcisang.core.state.Resource
import com.wcisang.home.paging.CharacterDataSourceFactory
import com.wcisang.home.usecase.GetCharactersUseCase

class MainViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val limit = 20
    val pagingState = MutableLiveData<Resource<Nothing>>()
    lateinit var characters: LiveData<PagedList<Character>>

    fun getCharacters() {
        val sourceFactory = CharacterDataSourceFactory(getCharactersUseCase,
            limit, viewModelScope, pagingState)
        characters = LivePagedListBuilder(sourceFactory, getPageListConfig()).build()
    }

    private fun getPageListConfig() =
        PagedList.Config.Builder()
            .setInitialLoadSizeHint(15)
            .setEnablePlaceholders(false)
            .setPageSize(limit)
            .build()
}