package com.wcisang.core.data.source

import com.wcisang.core.data.source.response.MarvelCharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("orderBy") orderBy: String = "-modified"
    ) : MarvelCharactersResponse
}