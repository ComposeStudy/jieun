package com.example.amphibiansapp.data

import javax.inject.Inject

class AmphibianRepositoryImpl(
    @Inject private val service: AmphibiansApiService,
) : AmphibianRepository {
    override suspend fun fetchAmphibianList(): List<Amphibian> = service.getAmphibians()
}