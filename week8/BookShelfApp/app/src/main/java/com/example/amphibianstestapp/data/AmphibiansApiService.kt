package com.example.amphibianstestapp.data

import com.example.amphibianstestapp.data.dto.BookResultDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AmphibiansApiService {
    @GET("books/v1/volumes")
    suspend fun getAmphibians(
        @Query("q")
        query:String
    ): BookResultDto
}
