package com.example.lunchorderapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class OrderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun setEntree(entree:String){
        _uiState.update { currentState ->
            currentState.copy(
                entree = entree,
            )
        }
    }

    fun setSideDish(sideDish:String){
        _uiState.update { currentState ->
            currentState.copy(
                sideDish = sideDish,
            )
        }
    }

    fun setAccompaniment(accompaniment:String){
        _uiState.update { currentState ->
            currentState.copy(
                accompaniment = accompaniment,
            )
        }
    }
}