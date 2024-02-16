package com.project.recipeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.recipeapp.databinding.PopularRvItemBinding

class PopularAdapter(var dataList: ArrayList<Recipe>, var context: Context):
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: PopularRvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PopularRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            Glide.with(context).load(dataList[position].img).into(holder.binding.popularImg)
        holder.binding.popularTxt.text = dataList.get(position).tittle
        val time = dataList[position].ing.split("\n".toRegex()).dropLastWhile { it.isEmpty()}.toTypedArray()
        holder.binding.popularTimeTxt.text = time[0]
    }
}