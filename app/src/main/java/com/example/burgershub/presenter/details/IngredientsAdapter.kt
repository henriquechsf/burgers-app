package com.example.burgershub.presenter.details

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.burgershub.databinding.IngredientItemBinding
import com.example.burgershub.domain.model.Ingredient
import com.squareup.picasso.Picasso

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    var ingredients: List<Ingredient> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            IngredientItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = ingredients.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ingredient = ingredients[position]

        Log.d("TESTINFO", ingredient.toString())
        holder.binding.textIngredientName.text = ingredient.name
        Picasso
            .get()
            .load(ingredient.img)
            .into(holder.binding.imageBurger)
    }

    inner class MyViewHolder(val binding: IngredientItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}