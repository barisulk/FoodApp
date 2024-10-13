package com.brs.foodapp.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.brs.foodapp.R
import com.brs.foodapp.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

// ui/cart/CartFragment.kt
@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private val viewModel: CartViewModel by viewModels()
    private lateinit var adapter: CartAdapter // Adapter'ı burada tanımlayın

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchCartItems("baris")  // Varsayılan kullanıcı adı

        binding.imageViewBackk.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.buttonCart.setOnClickListener{
            Toast.makeText(requireContext(), "Siparişiniz Onaylandı", Toast.LENGTH_SHORT).show()
        }

        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())

        // Adapter'ı burada oluşturun
        adapter = CartAdapter(emptyList()) { cartItem ->
            viewModel.removeItem(cartItem.sepet_yemek_id, "baris")
        }
        binding.rvCart.adapter = adapter // Adapter'ı bağlayın

        viewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            adapter.updateData(cartItems) // Verileri güncellemek için updateData metodunu çağırın
        }
    }
}
