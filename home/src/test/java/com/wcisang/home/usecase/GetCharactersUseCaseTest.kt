package com.wcisang.home.usecase

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.wcisang.core.data.repository.MarvelRepository
import com.wcisang.core.state.Resource
import com.wcisang.home.utils.DataFactory
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetCharactersUseCaseTest {

    private val repository: MarvelRepository = mock()
    private val useCase = GetCharactersUseCase(repository)

    @Test
    fun `should return a list`() {
        runBlocking {
            whenever(repository.getCharacters(any(), any()))
                .thenReturn(DataFactory.getMarvelResponse())

            val result = useCase.execute(GetCharactersUseCase.Params.forCharacter(1,1))
            Assert.assertEquals(result.status, Resource.Status.SUCCESS)
        }

    }

    @Test
    fun `should return a error`() {
        runBlocking {
            whenever(repository.getCharacters(any(), any()))
                .thenAnswer {throw Exception("error")}

            val result = useCase.execute(GetCharactersUseCase.Params.forCharacter(1,1))
            Assert.assertEquals(result.status, Resource.Status.ERROR)
            Assert.assertEquals(result.messageError, "error")
        }

    }

    @Test
    fun `should return a coroutineCancel`() {
        runBlocking {
            whenever(repository.getCharacters(any(), any()))
                .thenAnswer {throw CancellationException("canceled")}

            val result = useCase.execute(GetCharactersUseCase.Params.forCharacter(1,1))
            Assert.assertEquals(result.status, Resource.Status.ERROR)
            Assert.assertEquals(result.messageError, "canceled")
        }

    }
}