package com.brs.foodapp.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.brs.foodapp.R
import com.brs.foodapp.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

// ui/detail/DetailFragment.kt
@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedFood = args.food

        // Yemek detayını doldur
        binding.textViewFoodNameD.text = selectedFood.yemek_adi
        binding.textViewFoodPriceD.text = "${selectedFood.yemek_fiyat} ₺"
        Glide.with(requireContext())
            .load("http://kasimadalan.pe.hu/yemekler/resimler/${selectedFood.yemek_resim_adi}")
            .into(binding.imageViewFoodD)

        binding.buttonFoodDel.setOnClickListener{
            var quantity = binding.textViewFoodQ.text.toString().toInt()
            if (quantity > 1) {
                quantity--
                binding.textViewFoodQ.text = quantity.toString()
            }
        }

        binding.buttonFoodAdd.setOnClickListener{
            var quantity = binding.textViewFoodQ.text.toString().toInt()
            quantity++
            binding.textViewFoodQ.text = quantity.toString()
        }

        binding.imageViewBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        // Sepete ekleme işlemi
        binding.buttonAddChart.setOnClickListener {
            val quantity = binding.textViewFoodQ.text.toString().toInt()
            viewModel.addToCart(selectedFood, quantity, "baris")  // Varsayılan kullanıcı adı
            Toast.makeText(context, "${quantity} adet ${selectedFood.yemek_adi} sepete eklendi!", Toast.LENGTH_SHORT).show()
            Log.d("DetailFragment", "Sepete eklenen yemek: ${selectedFood.yemek_adi} - Adet: $quantity")
        }
    }
}
