package com.example.task.ui.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class BrandResponse(
    @SerializedName("data") val products: List<Product>,
    val brand: Brand
)

data class ProductDetailsResponse(
    @SerializedName("data") val product: Product,
)

@Parcelize
data class Brand(
    val id: String,
    val name: String,
    val banner: String,
    val logo: String,
    val description: String
) : Parcelable
