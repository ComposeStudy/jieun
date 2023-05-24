package com.example.amphibiansapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibiansapp.data.AmphibianRepository
import com.example.amphibiansapp.domain.UiState
import com.example.amphibiansapp.domain.fetchAmphibianUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmphibianViewModel @Inject constructor(
    private val fetchAmphibianUseCase: fetchAmphibianUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        fetchAmphibianList()
    }

    private fun fetchAmphibianList(){
        viewModelScope.launch {
            kotlin.runCatching {
                 fetchAmphibianUseCase().collect {
                    _state.value = UiState.Success(it)
                 }
            }
        }
    }
}