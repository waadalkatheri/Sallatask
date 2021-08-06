package com.example.task.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Currency", "SAR")
            .addHeader("AppVersion", "3.0.0")
            .addHeader("Store-Identifier", "1328842359")
            .build()
        return chain.proceed(newRequest)
    }
}