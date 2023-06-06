package com.example.amphibianstestapp.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndustryIdentifier(
    @SerialName("identifier")
    val identifier: String? = null,
    @SerialName("type")
    val type: String? = null
)