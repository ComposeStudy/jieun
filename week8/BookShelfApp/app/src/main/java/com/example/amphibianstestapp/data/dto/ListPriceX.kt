package com.example.amphibianstestapp.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListPriceX(
    @SerialName("amountInMicros")
    val amountInMicros: Long? = null,
    @SerialName("currencyCode")
    val currencyCode: String? = null
)