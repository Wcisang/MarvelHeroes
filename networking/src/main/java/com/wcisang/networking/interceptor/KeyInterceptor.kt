package com.wcisang.networking.interceptor

import com.wcisang.networking.util.AuthApi
import okhttp3.Interceptor
import okhttp3.Response

class KeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val authApi = AuthApi()
        val url = request.url
            .newBuilder()
            .addQueryParameter("apikey", authApi.apiPublicKey)
            .addQueryParameter("ts", authApi.timeStamp.toString())
            .addQueryParameter("hash", authApi.md5Hash)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}