package com.example.burgershub.data.repository

import com.example.burgershub.data.api.ServiceApi
import com.example.burgershub.data.mapper.toDomain
import com.example.burgershub.domain.model.Burger
import com.example.burgershub.domain.repository.BurgerRepository
import javax.inject.Inject

class BurgerRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
) : BurgerRepository {

    override suspend fun getBurgers(): List<Burger> {
        return serviceApi.getBurgers().map { it.toDomain() }
    }

    override suspend fun getBurgerById(id: Int): Burger {
        return serviceApi.getBurgerById(id).toDomain()
    }

    override suspend fun getBurgerByName(name: String): List<Burger> {
        return serviceApi.getBurgerByName(name).map { it.toDomain() }
    }
}