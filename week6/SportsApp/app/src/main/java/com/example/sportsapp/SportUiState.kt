package com.example.sportsapp

import com.example.sportsapp.data.Sport

data class SportUiState(
    val currentSelectedItem:Sport = Sport(),
    val sports: List<Sport> = emptyList(),
    val isFirstScreen : Boolean = true
)