package com.example.task.ui.brand.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task.databinding.ItemProductBinding
import com.example.task.ui.models.Product

class ProductsAdapter(private val productsListClicks: ProductsListClicks) :
    PagingDataAdapter<Product, ProductsAdapter.ProductViewHolder>(DiffCheck.Currency_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {


        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it, productsListClicks) }
    }
///////
    class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bindTo(product: Product, productsListClicks: ProductsListClicks) {
            binding.apply {
                this.product = product
                this.productClick = productsListClicks
                buttomProduct.setBackgroundColor(Color.BLACK)

                tvProductPromo.isVisible = product.promotion.title?.isNotEmpty() ?: false
                tvProductOffer.isVisible = product.promotion.subTitle?.isNotEmpty() ?: false
                tvProductPromo.rotation = 40.0f
                tvProductPromo.setBackgroundColor(Color.BLACK );






            }
        }
    }

}

object DiffCheck {
    var Currency_DIFF_CALLBACK: DiffUtil.ItemCallback<Product> =
        object :
            DiffUtil.ItemCallback<Product>() {

            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }
        }
}

interface ProductsListClicks {
    fun onProductClicked(product: Product)
}
