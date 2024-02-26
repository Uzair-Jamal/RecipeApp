package com.project.recipeapp

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.project.recipeapp.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    var imgCrop = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImg)
        binding.title.text = intent.getStringExtra("title")
        binding.stepsData.text = intent.getStringExtra("des")

        val ing = intent.getStringExtra("ing")!!.split("\n".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        binding.time.text = ing[0]
        for(i in 1 until ing.size){
            binding.ingData.text =
                """${binding.ingData.text} . ${ing[i]}
                    
                """.trimIndent()
        }

        binding.stepsBtn.background  = null
        binding.stepsBtn.setTextColor(getColor(R.color.black))
        binding.stepsBtn.setOnClickListener {
            binding.stepsBtn.setBackgroundResource(R.drawable.btn_ing)
            binding.stepsBtn.setTextColor(getColor(R.color.white))
            binding.ingBtn.setTextColor(getColor(R.color.white))
            binding.ingBtn.background = null
            binding.stepsScroll.visibility = View.VISIBLE
            binding.ingScroll.visibility = View.GONE
        }

        binding.ingBtn.setOnClickListener {
            binding.ingBtn.setBackgroundResource(R.drawable.btn_ing)
            binding.ingBtn.setTextColor(getColor(R.color.white))
            binding.stepsBtn.setTextColor(getColor(R.color.black))
            binding.stepsBtn.background = null
            binding.stepsScroll.visibility = View.GONE
            binding.ingScroll.visibility = View.VISIBLE
        }

        binding.zoomIv.setOnClickListener {
            if(imgCrop){
                binding.itemImg.scaleType = ImageView.ScaleType.FIT_CENTER
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImg)
                binding.shadow.visibility = View.GONE
                binding.zoomIv.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_ATOP)
                imgCrop = !imgCrop
            }
            else{
                binding.itemImg.scaleType = ImageView.ScaleType.CENTER_CROP
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImg)
                binding.shadow.visibility = View.VISIBLE
                binding.zoomIv.colorFilter = null
                imgCrop = !imgCrop
            }
        }
        binding.backBtn!!.setOnClickListener {
            finish()
        }
    }
}