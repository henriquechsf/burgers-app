package com.example.burgershub.domain.usecase

import com.example.burgershub.domain.model.Burger
import com.example.burgershub.domain.repository.BurgerRepository
import javax.inject.Inject

class GetBurgerByIdUseCase @Inject constructor(
    private val burgerRepository: BurgerRepository
) {

    suspend operator fun invoke(id: Int): Burger {
        return burgerRepository.getBurgerById(id)
    }
}