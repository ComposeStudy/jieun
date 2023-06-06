package com.example.amphibianstestapp.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    @SerialName("finskyOfferType")
    val finskyOfferType: Int? = null,
    @SerialName("listPrice")
    val listPrice: ListPriceX? = null,
    @SerialName("retailPrice")
    val retailPrice: RetailPrice? = null
)