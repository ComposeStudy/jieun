package com.example.amphibiansapp.domain

import com.example.amphibiansapp.data.Amphibian
import com.example.amphibiansapp.data.AmphibianRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class fetchAmphibianUseCase(
    private val repository: AmphibianRepository
) {
    suspend operator fun invoke(): Flow<List<Amphibian>> = flow{
        repository.fetchAmphibianList()
    }
}