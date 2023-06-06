package com.example.amphibianstestapp.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResultDto(
    @SerialName("items")
    val items: List<Item>? = null,
    @SerialName("kind")
    val kind: String? = null,
    @SerialName("totalItems")
    val totalItems: Int? = null
)