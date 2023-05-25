package com.example.amphibianstestapp.domain

import com.example.amphibianstestapp.data.Amphibian

sealed class UiState {
    object Loading : UiState()
    data class Success(val amphibianList: List<Amphibian>): UiState()
}