package com.example.burgershub.data.api

import com.example.burgershub.data.model.BurgerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    @GET("burgers")
    suspend fun getBurgers(): List<BurgerResponse>

    @GET("burgers/{id}")
    suspend fun getBurgerById(@Path("id") id: Int): BurgerResponse

    @GET("find-burger/")
    suspend fun getBurgerByName(@Query("search") name: String): List<BurgerResponse>
}