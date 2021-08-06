package com.example.task.ui.productDetails.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.task.databinding.FragmentProdectDetailsBinding
import com.example.task.ui.productDetails.viewmodels.ProductDetailsViewState
import com.example.task.ui.productDetails.viewmodels.ProductViewModel
import com.example.task.ui.utils.ViewPagerAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class ProductDetailsFragment : Fragment() {
    lateinit var binding: FragmentProdectDetailsBinding
    lateinit var mViewPagerAdapter: ViewPagerAdapter

    // inject view model using koin
    private val viewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val id = it.getString("product_id")
            viewModel.getProductDetails(id!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProdectDetailsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeValues()
        initializeObservable()
    }

    private fun initializeObservable() {
        viewModel.productDetailsDetails.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    private fun render(detailsViewState: ProductDetailsViewState) {
        when (detailsViewState) {
            is ProductDetailsViewState.LOADING -> binding.pbLoading.isVisible = true
            is ProductDetailsViewState.SUCCESS -> {

                binding.apply {
                    pbLoading.isVisible = false
                    mViewPagerAdapter = ViewPagerAdapter(
                        requireContext(),
                        detailsViewState.payload.product.images!!
                    )
                    response = detailsViewState.payload
                    tvProductPromo.isVisible =
                        detailsViewState.payload.product.promotion.title?.isNotEmpty() ?: false
                    tvProductOffer.isVisible =
                        detailsViewState.payload.product.promotion.subTitle?.isNotEmpty() ?: false
                    photosViewpager.adapter = mViewPagerAdapter
                }
            }
            is ProductDetailsViewState.FAILURE -> {
                binding.pbLoading.isVisible = false
                Toast.makeText(requireContext(), detailsViewState.errorMsg, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun initializeValues() {
        binding.tabLayout.setupWithViewPager(binding.photosViewpager)
    }


}