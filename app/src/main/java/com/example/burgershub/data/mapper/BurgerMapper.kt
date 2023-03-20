package com.example.burgershub.data.mapper

import com.example.burgershub.data.model.BurgerResponse
import com.example.burgershub.data.model.ImageResponse
import com.example.burgershub.data.model.IngredientResponse
import com.example.burgershub.domain.model.Burger
import com.example.burgershub.domain.model.Image
import com.example.burgershub.domain.model.Ingredient

fun BurgerResponse.toDomain() = Burger(
    id = this.id,
    name = this.name,
    desc = this.desc,
    price = this.price,
    veg = this.veg,
    images = this.imageResponses?.map { it?.toDomain() },
    ingredients = this.ingredientResponses?.map { it?.toDomain() }
)

fun ImageResponse.toDomain() = Image(
    lg = this.lg,
    sm = this.sm
)

fun IngredientResponse.toDomain() = Ingredient(
    id = this.id,
    img = this.img,
    name = this.img
)