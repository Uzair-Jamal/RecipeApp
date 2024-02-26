package com.project.recipeapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.recipeapp.databinding.ActivityCategoryBinding
import com.project.recipeapp.databinding.CategoryRvBinding

class CategoryAdapter(var categoryList: ArrayList<Recipe>, var context: Context): RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: CategoryRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = CategoryRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
      return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(categoryList[position].img).into(holder.binding.categoryImg)
        holder.binding.categoryTitle.text = categoryList[position].tittle
        holder.binding.categoryTime.text = categoryList[position].ing
        var temp = categoryList[position].ing.split("\n").dropLastWhile { it.isEmpty() }.toTypedArray()
        holder.binding.categoryTime.text = temp[0]

        holder.binding.nextBtn.setOnClickListener {
            var intent = Intent(context, RecipeActivity::class.java)
            intent.putExtra("img",categoryList[position].img)
            intent.putExtra("title",categoryList[position].tittle)
            intent.putExtra("des",categoryList[position].des)
            intent.putExtra("ing",categoryList[position].ing)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}