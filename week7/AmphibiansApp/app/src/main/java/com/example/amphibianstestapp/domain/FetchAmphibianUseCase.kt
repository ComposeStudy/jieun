package com.example.amphibianstestapp.domain

import com.example.amphibianstestapp.data.Amphibian
import com.example.amphibianstestapp.data.AmphibianRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchAmphibianUseCase @Inject constructor(
    private val repository: AmphibianRepository
) {
    suspend operator fun invoke(): Flow<List<Amphibian>> = flow{
        emit(repository.fetchAmphibianList())
    }
}