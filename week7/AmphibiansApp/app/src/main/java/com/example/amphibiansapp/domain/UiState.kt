package com.example.amphibiansapp.domain

import com.example.amphibiansapp.data.Amphibian

sealed class UiState {
    object Error : UiState()
    object Loading : UiState()
    data class Success(val amphibianList: List<Amphibian>):UiState()
}