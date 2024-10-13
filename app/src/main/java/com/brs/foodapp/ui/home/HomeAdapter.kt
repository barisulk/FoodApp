package com.brs.foodapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brs.foodapp.data.model.Food
import com.brs.foodapp.databinding.FoodCardBinding
import com.bumptech.glide.Glide

// ui/home/HomeAdapter.kt
class HomeAdapter(private var foods: List<Food>, private val onFoodClick: (Food) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(val binding: FoodCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = FoodCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foods[position]
        holder.binding.textViewFoodName.text = food.yemek_adi
        holder.binding.textViewFoodPrice.text = "${food.yemek_fiyat} ₺"
        Glide.with(holder.itemView.context)
            .load("http://kasimadalan.pe.hu/yemekler/resimler/${food.yemek_resim_adi}")
            .into(holder.binding.imageViewFood)

        holder.itemView.setOnClickListener { onFoodClick(food) }

        holder.binding.imageViewFoodAdd.setOnClickListener { onFoodClick(food) }
    }

    override fun getItemCount(): Int = foods.size

    fun updateData(newFoods: List<Food>) {
        foods= newFoods
        notifyDataSetChanged() // Değişiklikleri bildirin
    }
}
