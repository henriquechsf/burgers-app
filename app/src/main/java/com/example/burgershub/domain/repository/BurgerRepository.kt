package com.example.burgershub.domain.repository

import com.example.burgershub.domain.model.Burger

interface BurgerRepository {

    suspend fun getBurgers(): List<Burger>

    suspend fun getBurgerById(id: Int): Burger

    suspend fun getBurgerByName(name: String): List<Burger>
}