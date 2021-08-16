package com.example.task.ui.brand.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.R
import com.example.task.data.remote.ApiClient
import com.example.task.databinding.FragmentBrandBinding
import com.example.task.ui.brand.adapters.ProductsAdapter
import com.example.task.ui.brand.adapters.ProductsListClicks
import com.example.task.ui.brand.repositories.BrandRepository
import com.example.task.ui.brand.viewmodels.BrandViewModel
import com.example.task.ui.brand.viewmodels.BrandViewState
import com.example.task.ui.brand.viewmodels.ProductViewState
import com.example.task.ui.models.Product
import com.example.task.utilities.viewModelFactory
import kotlinx.coroutines.launch


class BrandFragment : Fragment(), ProductsListClicks {
    lateinit var binding: FragmentBrandBinding
    lateinit var productsAdapter: ProductsAdapter
    private val viewModel: BrandViewModel by viewModels {
        // manual injection for view model, repository, api interface
        viewModelFactory {
            BrandViewModel(BrandRepository(ApiClient.PRODUCTS_REMOTE))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBrandBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeValues()
        initializeObservable()
        getUiData()
    }

    private fun getUiData() {
        viewModel.apply {
            getBrand()
            getProducts()
        }
    }

    private fun initializeObservable() {
        viewModel.products.observe(viewLifecycleOwner, {
            renderProducts(it)
        })
        viewModel.brand.observe(viewLifecycleOwner, {
            renderBrand(it)
        })
    }

    // to render ui
    private fun renderProducts(viewState: ProductViewState) {
        when (viewState) {
            is ProductViewState.LOADING -> binding.pbLoading.isVisible = true
            is ProductViewState.SUCCESS -> {
                lifecycleScope.launch {
                    productsAdapter.submitData(viewState.payload)
                }

            }
            is ProductViewState.FAILURE -> {
                binding.pbLoading.isVisible = false
                Toast.makeText(requireContext(), viewState.errorMsg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun renderBrand(viewState: BrandViewState) {
        when (viewState) {
            is BrandViewState.LOADING -> binding.pbLoading.isVisible = true
            is BrandViewState.SUCCESS -> {
                binding.apply {
                    pbLoading.isVisible = false
                    cardView.isVisible = true
                    brand = viewState.payload
                }
            }
            is BrandViewState.FAILURE -> {
                binding.pbLoading.isVisible = false
                Toast.makeText(requireContext(), viewState.errorMsg, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun initializeValues() {
        productsAdapter = ProductsAdapter(this)
        binding.rvProducts.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
            adapter = productsAdapter
        }
    }


    override fun onProductClicked(product: Product) {
        val bundle = bundleOf("product_id" to product.id)
        view?.findNavController()?.navigate(R.id.prodectDetailsFragment, bundle)
    }


}