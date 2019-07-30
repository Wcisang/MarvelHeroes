package com.wcisang.marvelheroes.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.wcisang.core.domain.model.Character
import com.wcisang.core.state.Resource
import com.wcisang.marvelheroes.usecase.GetCharactersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CharacterDataSource (
    private val getCharactersUseCase: GetCharactersUseCase,
    private val limit: Int,
    private val scope: CoroutineScope,
    private val state : MutableLiveData<Resource<Nothing>>
): PageKeyedDataSource<Int, Character>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Character>) {
        executeQuery(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        val page = params.key
        executeQuery(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {}

    private fun executeQuery(page: Int, callback:(List<Character>) -> Unit) {
        state.postValue(Resource.loading())
        scope.launch {
            val result = getCharactersUseCase.execute(
                GetCharactersUseCase.Params.forCharacter(limit, page))
            if (result.status == Resource.Status.SUCCESS) {
                callback(result.data!!)
            }else {
                state.postValue(Resource.error(message = result.messageError!!))
            }
        }
    }
}