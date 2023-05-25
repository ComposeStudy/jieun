package com.example.amphibianstestapp.data

import javax.inject.Inject

class AmphibianRepositoryImpl @Inject constructor(
    private val service: AmphibiansApiService,
) : AmphibianRepository {
    override suspend fun fetchAmphibianList(): List<Amphibian> = service.getAmphibians()
}