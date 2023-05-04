package com.example.lunchorderapp.order

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.lunchorderapp.OrderUiState

@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState
) {
    Text(text = "${orderUiState.entree}, ${orderUiState.sideDish}, ${orderUiState.accompaniment}")
}