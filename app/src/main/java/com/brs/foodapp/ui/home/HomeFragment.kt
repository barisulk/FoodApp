package com.brs.foodapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.brs.foodapp.R
import com.brs.foodapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/// ui/home/HomeFragment.kt
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeAdapter // Adapter'ı sınıf düzeyinde tanımlayın

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageViewCart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }

        binding.rvFood.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        // Adapter'ı burada oluşturun
        adapter = HomeAdapter(emptyList()) { food ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(food)
            findNavController().navigate(action)
        }
        binding.rvFood.adapter = adapter

        viewModel.fetchAllFoods() // Verileri çek

        // Veriler geldikçe adapter'ı güncelleyin
        viewModel.foods.observe(viewLifecycleOwner) { foods ->
            adapter.updateData(foods) // Adapter'a verileri güncelleyin
        }
    }


}

