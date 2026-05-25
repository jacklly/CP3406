package com.example.playground

import android.util.Log.println

fun main() {
    val recipe = Recipe() // Create an instance of Recipe class
    recipe.addIngredient("Rice") // Call the method with argument “Rice”
    recipe.addIngredient("Beans") // Call it again, with “Beans”
    println(recipe.getIngredients()) // Print this method to console
}

class Recipe() {
    private val ingredients = mutableListOf<String>()
    fun addIngredient(name: String) {
        ingredients.add(name)
    }
    fun getIngredients(): List<String> {
        return ingredients
    }
}