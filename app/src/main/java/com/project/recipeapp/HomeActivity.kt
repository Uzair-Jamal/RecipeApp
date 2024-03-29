package com.project.recipeapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.recipeapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var dataList:ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        binding.ivMenu.setOnClickListener {
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.bottom_sheet)
            dialog.show()
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setGravity(Gravity.BOTTOM)
        }

        binding.searchView.setOnClickListener {
            startActivity(Intent(this,SearchActivity::class.java))
        }
        binding.saladIv.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("TITLE","Salad")
            intent.putExtra("CATEGORY","Salad")
            startActivity(intent)
        }
        binding.mainIv.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("TITLE","Main Dish")
            intent.putExtra("CATEGORY","Dish")
            startActivity(intent)

        }
        binding.drinksIv.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("TITLE","Drinks")
            intent.putExtra("CATEGORY","Drinks")
            startActivity(intent)

        }
        binding.dessertIv.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("TITLE","Desserts")
            intent.putExtra("CATEGORY","Desserts")
            startActivity(intent)
        }
    }
    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.rvPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        var db = Room.databaseBuilder(this@HomeActivity,AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject = db.getDao()
        var recipes = daoObject.getAll()
        for(i in recipes!!.indices){
            if(recipes[i]!!.category.contains("Popular")){
                dataList.add(recipes[i]!!)
            }
            popularAdapter = PopularAdapter(dataList,this)
            binding.rvPopular.adapter = popularAdapter
            }
        }
    }