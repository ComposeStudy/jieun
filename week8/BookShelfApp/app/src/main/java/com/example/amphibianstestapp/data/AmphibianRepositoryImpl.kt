package com.example.amphibianstestapp.data

import com.example.amphibianstestapp.data.dto.BookResultDto
import javax.inject.Inject

class AmphibianRepositoryImpl @Inject constructor(
    private val service: AmphibiansApiService,
) : AmphibianRepository {
    override suspend fun fetchAmphibianList(query: String): BookResultDto {
        return service.getAmphibians(query)
    }
}