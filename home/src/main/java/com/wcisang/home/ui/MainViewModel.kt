package com.wcisang.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.wcisang.core.data.source.CharacterDataSource
import com.wcisang.core.domain.model.Character
import com.wcisang.core.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val limit = 20

    fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = getPagingConfig(),
            pagingSourceFactory = { CharacterDataSource(getCharactersUseCase) }
        ).flow.cachedIn(viewModelScope)
    }

    private fun getPagingConfig() =
        PagingConfig(
            pageSize = limit,
            initialLoadSize = 15,
            enablePlaceholders = false
        )
}