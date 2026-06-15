package com.example.playground.com.example.myapplication

class Recipe {
    private val ingredients = mutableListOf<String>()
    fun addIngredient(name: String) {
        ingredients.add(name)
    }
    fun getIngredients(): List<String> {
        return ingredients
    }
}

fun main() {
    val recipe = Recipe() // Create an instance of Recipe class
    recipe.addIngredient("Rice") // Call the method with argument “Rice”
    recipe.addIngredient("Beans") // Call it again, with “Beans”
    println(recipe.getIngredients()) // Print this method to console
}
