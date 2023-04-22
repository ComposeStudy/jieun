package com.example.artspaceapp

import androidx.annotation.DrawableRes

class ChoonSikDataStore{
    fun getChoonSikList() = listOf(
        ChoonSik(R.drawable.choonsik_01,"멍"),
        ChoonSik(R.drawable.choonsik_02,"히"),
        ChoonSik(R.drawable.choonsik_03,"당황"),
    )
}

data class ChoonSik(
    @DrawableRes val imageResourceId: Int,
    val description:String
)