package com.example.amphibianstestapp.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pdf(
    @SerialName("isAvailable")
    val isAvailable: Boolean? = null
)