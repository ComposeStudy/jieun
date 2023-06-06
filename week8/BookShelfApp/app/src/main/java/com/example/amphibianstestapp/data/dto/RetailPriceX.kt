package com.example.amphibianstestapp.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RetailPriceX(
    @SerialName("amount")
    val amount: Int? = null,
    @SerialName("currencyCode")
    val currencyCode: String? = null
)