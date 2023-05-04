package com.example.lunchorderapp

data class OrderUiState(
    val entree: String = "",
    val sideDish: String = "",
    val accompaniment: String = "",
    val pickupOptions: List<String> = listOf()
)