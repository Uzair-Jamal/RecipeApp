package com.project.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.project.recipeapp.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityCategoryBinding.inflate(layoutInflater)
    }
    private lateinit var categoryList: ArrayList<Recipe>
    private lateinit var categoryAdapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = intent.getStringExtra("TITTLE")
        setUpRecyclerView()
        binding.categoryBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun setUpRecyclerView() {
        categoryList = ArrayList()
        binding.categoryRv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        var db = Room.databaseBuilder(this@CategoryActivity,AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject = db.getDao()
        var recipes = daoObject.getAll()
        for(i in recipes!!.indices){
            if (recipes[i]!!.category.contains(intent.getStringExtra("CATEGORY")!!)){
                categoryList.add(recipes[i]!!)
            }
            categoryAdapter = CategoryAdapter(categoryList,this)
            binding.categoryRv.adapter = categoryAdapter
        }
    }
}