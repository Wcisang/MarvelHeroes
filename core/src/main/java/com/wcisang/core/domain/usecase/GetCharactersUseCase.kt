package com.wcisang.core.domain.usecase

import com.wcisang.core.data.repository.MarvelRepository
import com.wcisang.core.domain.model.Character

class GetCharactersUseCase (
    private val marvelRepository: MarvelRepository
): UseCase<List<Character>, GetCharactersUseCase.Params>(){

    override suspend fun executeOnBackground(params: Params?): List<Character> {
        requireNotNull(params)
        val response =  marvelRepository.getCharacters(params.limit, params.offset)
        return response.data.results
    }

    data class Params constructor(val limit: Int, val offset: Int){
        companion object {
            fun forCharacter(limit: Int, offset: Int) : Params {
                return Params(
                    limit,
                    offset
                )
            }
        }
    }
}