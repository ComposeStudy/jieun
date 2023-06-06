package com.example.amphibianstestapp.domain

data class BookInfo(
    val id: String,
    val thumbnail: String,
    val link: String,
    val title: String,
    val author: List<String>,
)