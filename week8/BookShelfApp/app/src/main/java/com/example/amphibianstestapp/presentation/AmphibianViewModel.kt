package com.example.amphibianstestapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibianstestapp.domain.FetchAmphibianUseCase
import com.example.amphibianstestapp.domain.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmphibianViewModel @Inject constructor(
    private val fetchAmphibianUseCase: FetchAmphibianUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        fetchAmphibianList("harry")
    }

    private fun fetchAmphibianList(query:String) {
        viewModelScope.launch {
            fetchAmphibianUseCase(query).collect {
                _state.value = UiState.Success(it)
            }
        }
    }
}