package com.example.task.ui.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: String,
    val name: String,
    val promotion: Promotion,
    val price: Price,
    val thumbnail: String?,
    val images: List<Image>?,
    val brand: Brand?
) : Parcelable

@Parcelize
data class Promotion(
    val title: String? = "",
    @SerializedName("sub_title") val subTitle: String? = ""
) : Parcelable

@Parcelize
data class Price(
    val amount: String,
    val currency: String
) : Parcelable

@Parcelize
data class Image(
    val id: String,
    val url: String,
) : Parcelable