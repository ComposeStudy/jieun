package com.example.amphibianstestapp.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SaleInfo(
    @SerialName("buyLink")
    val buyLink: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("isEbook")
    val isEbook: Boolean? = null,
    @SerialName("listPrice")
    val listPrice: ListPrice? = null,
    @SerialName("offers")
    val offers: List<Offer>? = null,
    @SerialName("retailPrice")
    val retailPrice: RetailPriceX? = null,
    @SerialName("saleability")
    val saleability: String? = null
)