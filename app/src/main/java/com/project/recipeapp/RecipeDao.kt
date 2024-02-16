package com.project.recipeapp

import androidx.room.Dao
import androidx.room.Query

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll():List<Recipe?>?
}