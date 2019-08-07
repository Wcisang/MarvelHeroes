package com.wcisang.core.data.repository

import com.nhaarman.mockitokotlin2.*
import com.wcisang.core.data.source.MarvelService
import com.wcisang.core.utils.DataFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.SocketTimeoutException

class MarvelRepositoryImplTest {

    private val service = mock<MarvelService>()
    private val marvelRepositoryImpl = MarvelRepositoryImpl(service)

    @Test
    fun `should return a list`() {
        runBlocking {
            whenever(service.getCharacters(1,1))
                .thenReturn(DataFactory.getMarvelResponse())

            val response = marvelRepositoryImpl.getCharacters(1,1)

            verify(service, times(1)).getCharacters(eq(1), eq(1), eq("-modified"))

            Assert.assertEquals(response.code, 200)
            Assert.assertEquals(response.data.results.size ,1)

        }
    }

    @Test(expected = SocketTimeoutException::class)
    fun `should return a timeout error`() {
        runBlocking {
            whenever(service.getCharacters(1,1))
                .thenAnswer {throw SocketTimeoutException()}

            verify(service, times(1)).getCharacters(eq(1), eq(1), eq("-modified"))

            marvelRepositoryImpl.getCharacters(1,1)
        }
    }
}