package com.example.amphibianstestapp.data

import com.example.amphibianstestapp.data.dto.BookResultDto

interface AmphibianRepository {
    suspend fun fetchAmphibianList(query:String): BookResultDto
}