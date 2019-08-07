package com.wcisang.core.data.source

import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import com.wcisang.core.di.serviceModule
import com.wcisang.networking.util.loadFile
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class MarvelServiceTest : KoinTest{

    private lateinit var mockServer: MockWebServer
    private val service: MarvelService by inject()

    @Before
    fun setUp() {
        this.configureMockServer()
        startKoin { modules(listOf(serviceModule)) }
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
        stopKoin()
    }

    private fun configureMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    @Test
    fun `should return a list`() {
        mockHttpResponse("200_get_characters.json", 200)
        runBlocking {
            val response = service.getCharacters(1,1)
            Assert.assertEquals(200, response.code)
            Assert.assertEquals(response.data.results.size, 1)
        }
    }

    private fun mockHttpResponse(fileName: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(loadFile(fileName))
    )


}