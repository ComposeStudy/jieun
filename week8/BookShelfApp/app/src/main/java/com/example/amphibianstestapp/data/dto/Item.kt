package com.example.amphibianstestapp.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("accessInfo")
    val accessInfo: AccessInfo? = null,
    @SerialName("etag")
    val etag: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("kind")
    val kind: String? = null,
    @SerialName("saleInfo")
    val saleInfo: SaleInfo? = null,
    @SerialName("searchInfo")
    val searchInfo: SearchInfo? = null,
    @SerialName("selfLink")
    val selfLink: String? = null,
    @SerialName("volumeInfo")
    val volumeInfo: VolumeInfo? = null
)