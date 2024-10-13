package com.brs.foodapp.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brs.foodapp.data.model.CartItem
import com.brs.foodapp.databinding.FoodCartBinding
import com.bumptech.glide.Glide

// ui/cart/CartAdapter.kt
class CartAdapter(
    private var cartItems: List<CartItem>,
    private val onRemoveClick: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: FoodCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = FoodCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.binding.textViewFoodNameC.text = cartItem.yemek_adi
        holder.binding.textViewFoodPriC.text = "${cartItem.yemek_fiyat} ₺"
        holder.binding.textViewFoodQC.text = "Adet: ${cartItem.yemek_siparis_adet}"
        holder.binding.textViewAllFoodP.text = "${cartItem.yemek_fiyat * cartItem.yemek_siparis_adet} ₺"
        holder.binding.imageViewFoodDel.setOnClickListener{onRemoveClick(cartItem)}

        Glide.with(holder.itemView.context)
            .load("http://kasimadalan.pe.hu/yemekler/resimler/${cartItem.yemek_resim_adi}")
            .into(holder.binding.imageView2)

        holder.binding.imageViewFoodDel.setOnClickListener { onRemoveClick(cartItem) }
    }

    fun updateData(newCartItems: List<CartItem>) {
        cartItems = newCartItems
        notifyDataSetChanged() // Değişiklikleri bildirin
    }

    override fun getItemCount(): Int = cartItems.size
}
