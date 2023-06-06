package com.example.amphibianstestapp.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PanelizationSummary(
    @SerialName("containsEpubBubbles")
    val containsEpubBubbles: Boolean? = null,
    @SerialName("containsImageBubbles")
    val containsImageBubbles: Boolean? = null
)