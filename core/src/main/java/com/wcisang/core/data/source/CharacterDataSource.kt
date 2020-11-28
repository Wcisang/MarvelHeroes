package com.wcisang.core.data.source

import androidx.paging.PagingSource
import com.wcisang.core.domain.model.Character
import com.wcisang.core.domain.usecase.GetCharactersUseCase
import com.wcisang.core.state.Resource
import retrofit2.HttpException
import java.io.IOException

private const val CHARACTER_STARTING_PAGE_INDEX = 1
private const val CHARACTER_LIMIT = 20

class CharacterDataSource (
    private val getCharactersUseCase: GetCharactersUseCase
): PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: CHARACTER_STARTING_PAGE_INDEX
        return try {
            val result = getCharactersUseCase.execute(
                GetCharactersUseCase.Params.forCharacter(CHARACTER_LIMIT, page))
            if (result.status == Resource.Status.SUCCESS && result.data != null) {
                LoadResult.Page(
                    data = result.data,
                    prevKey = if (page == CHARACTER_STARTING_PAGE_INDEX) null else page - 1,
                    nextKey = if (result.data.isNullOrEmpty()) null else page + 1
                )
            }else {
                throw IOException(result.messageError)
            }
        }catch (io: IOException) {
            LoadResult.Error(io)
        }catch (http: HttpException) {
            LoadResult.Error(http)
        }
    }
}