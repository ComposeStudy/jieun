package com.example.amphibianstestapp.domain

import com.example.amphibianstestapp.data.AmphibianRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchAmphibianUseCase @Inject constructor(
    private val repository: AmphibianRepository,
) {
    suspend operator fun invoke(query: String): Flow<List<BookInfo>> = flow {
        val bookInfoList = mutableListOf<BookInfo>()
        repository.fetchAmphibianList(query).items?.map { BookDto ->
            bookInfoList.add(
                BookInfo(
                    id = BookDto.id ?: "",
                    thumbnail = BookDto.volumeInfo?.imageLinks?.thumbnail ?: "",
                    link = BookDto.selfLink ?: "",
                    title = BookDto.volumeInfo?.title ?: "",
                    author = BookDto.volumeInfo?.authors ?: emptyList()
                )
            )
        }
        emit(bookInfoList)
    }
}