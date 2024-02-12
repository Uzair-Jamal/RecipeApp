package com.project.recipeapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
class Recipe (
    @JvmField
    @PrimaryKey(autoGenerate = true)
    var uid: Int,
    var img: String,
    var tittle: String,
    var des: String,
    var ing: String,
    var category: String
)