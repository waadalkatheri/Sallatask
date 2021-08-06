package com.example.task.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// a network object used for manual injection of retrofit (apo calling)
object ApiClient {
    private const val BASE_URL: String = "https://salla.sa/api/v1/"

    private val gson : Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )

            .addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("Currency", "SAR")
                    builder.header("AppVersion", "3.0.0")
                    builder.header("Store-Identifier", "1328842359")
                    return@Interceptor chain.proceed(builder.build())
                }
            ).build()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val PRODUCTS_REMOTE : StoreRemote by lazy{
        retrofit.create(StoreRemote::class.java)
    }


}