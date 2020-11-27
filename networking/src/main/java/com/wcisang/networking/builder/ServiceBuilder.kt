package com.wcisang.networking.builder

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.wcisang.networking.interceptor.KeyInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceBuilder {

    private const val BASE_URL = "https://gateway.marvel.com:443/"

    fun <T> createService(service: Class<T>): T {
        return with(Retrofit.Builder()) {
            baseUrl(BASE_URL)
            client(getOkHttpClient())
            addConverterFactory(MoshiConverterFactory.create())
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            build()
        }.create(service)
    }

    private fun getOkHttpClient() =
        OkHttpClient
            .Builder()
            .addInterceptor(KeyInterceptor())
            .build()
}