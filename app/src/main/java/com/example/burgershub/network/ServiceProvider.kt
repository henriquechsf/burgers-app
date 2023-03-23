package com.example.burgershub.network

import android.content.Context
import io.github.brunogabriel.mockpinterceptor.MockPInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ServiceProvider @Inject constructor(
    context: Context
) {

    private val baseUrl = "https://burgers-hub.p.rapidapi.com/"

    val mockInterceptor = MockPInterceptor
        .Builder(context)
        .addDelayInMillis(1_000L, 1_000L)
        .build()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(Interceptor { chain ->
            chain.run {
                proceed(
                    request()
                        .newBuilder()
                        .addHeader(
                            name = "X-RapidAPI-Key",
                            value = "fd0f761f82mshe15b14a02cab83dp1c89c4jsn8089a23af69f"
                        )
                        .build()
                )
            }
        })
        .addInterceptor(mockInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build();

    fun <T> createService(apiClass: Class<T>): T = retrofit.create(apiClass)
}