package com.example.amphibianstestapp.data

interface AmphibianRepository {
    suspend fun fetchAmphibianList():List<Amphibian>
}