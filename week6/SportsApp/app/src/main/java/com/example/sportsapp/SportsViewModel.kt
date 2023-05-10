package com.example.sportsapp

import androidx.lifecycle.ViewModel
import com.example.sportsapp.data.DataSource
import com.example.sportsapp.data.Sport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SportsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SportUiState())
    val uiState: StateFlow<SportUiState> = _uiState.asStateFlow()

    init {
        initList()
    }

    private fun initList() {

        _uiState.value =
            SportUiState(
                sports = DataSource().getSportsItems(),
                currentSelectedItem = DataSource().getSportsItems()[0]
            )

    }

    fun updateDetailsScreenStates(sport:Sport){
        _uiState.value =
            SportUiState(
                sports = DataSource().getSportsItems(), // FIXME 임시방편
                currentSelectedItem = sport,
                isFirstScreen = false
            )
    }

    fun onBackPressed(){
        initList()
    }
}