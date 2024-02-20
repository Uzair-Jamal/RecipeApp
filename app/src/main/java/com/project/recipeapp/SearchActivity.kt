package com.project.recipeapp

import android.annotation.SuppressLint
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.recipeapp.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var rvAdapter: SearchAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private lateinit var recipes: List<Recipe?>


    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.search.requestFocus()
        var db = Room.databaseBuilder(this@SearchActivity,AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject = db.getDao()
        recipes = daoObject.getAll()!!
        setUpRecyclerView()
        binding.search.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(s.toString()!=""){
                    filterData(s.toString())
                }else{
                    setUpRecyclerView()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.backBtnSearch.setOnClickListener {
            finish()
        }

    }

    private fun filterData(filterText: String) {
        var filterData = ArrayList<Recipe>()
        for(i in recipes.indices){
            if(recipes[i]!!.tittle.lowercase().contains(filterText.lowercase())){
                filterData.add(recipes[i]!!)
            }
            rvAdapter.filterList(filterData)
        }
    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()

        binding.searchRv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        for(i in recipes.indices){
            if(recipes[i]!!.category.contains("Popular")){
                dataList.add(recipes[i]!!)
            }
            rvAdapter = SearchAdapter(dataList,this)
            binding.searchRv.adapter = rvAdapter
        }
    }
}