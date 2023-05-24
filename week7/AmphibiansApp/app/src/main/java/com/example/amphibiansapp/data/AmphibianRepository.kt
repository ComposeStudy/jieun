package com.example.amphibiansapp.data

interface AmphibianRepository {
    suspend fun fetchAmphibianList():List<Amphibian>
}