package ru.urfu.droidpractice1.data

data class Recipe(
    val ingredients: List<Ingredient>,
    val steps: List<Step>
)

data class Ingredient(
    val name: String,
    val amount: String
)

data class Step(
    val imageUrl: String,
    val description: String
)
