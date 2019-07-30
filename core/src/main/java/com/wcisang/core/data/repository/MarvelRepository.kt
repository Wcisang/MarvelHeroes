package com.wcisang.core.data.repository

import com.wcisang.core.data.source.response.MarvelCharactersResponse

interface MarvelRepository {

    suspend fun getCharacters(
        limit: Int,
        offset: Int
    ): MarvelCharactersResponse
}