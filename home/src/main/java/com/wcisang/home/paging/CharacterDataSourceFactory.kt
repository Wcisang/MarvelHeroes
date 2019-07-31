package com.wcisang.home.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.wcisang.core.domain.model.Character
import com.wcisang.core.state.Resource
import com.wcisang.home.usecase.GetCharactersUseCase
import kotlinx.coroutines.CoroutineScope

class CharacterDataSourceFactory (
    private val getCharactersUseCase: GetCharactersUseCase,
    private val limit: Int,
    private val scope: CoroutineScope,
    private val pagingState : MutableLiveData<Resource<Nothing>>
): DataSource.Factory<Int, Character>(){

    override fun create(): DataSource<Int, Character> {
        return CharacterDataSource(
            getCharactersUseCase,
            limit, scope, pagingState
        )
    }
}