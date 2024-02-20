package com.project.recipeapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.recipeapp.databinding.SearchRvBinding

class SearchAdapter(var dataList:ArrayList<Recipe>, var context: Context): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: SearchRvBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SearchRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterList: ArrayList<Recipe>){
        dataList = filterList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }



    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList[position].img).into(holder.binding.searchImg)
        holder.binding.searchTxt.text = dataList[position].tittle
    }
}