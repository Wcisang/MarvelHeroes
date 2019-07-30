package com.wcisang.core.data.repository

import com.wcisang.core.data.source.MarvelService
import com.wcisang.core.data.source.response.MarvelCharactersResponse

class MarvelRepositoryImpl (val service: MarvelService):  MarvelRepository{

    override suspend fun getCharacters(limit: Int, offset: Int): MarvelCharactersResponse {
        return service.getCharacters(limit, offset)
    }
}