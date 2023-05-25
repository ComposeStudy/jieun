package com.example.amphibianstestapp.di

import com.example.amphibianstestapp.data.AmphibianRepository
import com.example.amphibianstestapp.data.AmphibianRepositoryImpl
import com.example.amphibianstestapp.data.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class AmphibianModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAmphibianRepository(
        repository: AmphibianRepositoryImpl,
    ): AmphibianRepository

    companion object {
        @Provides
        @ViewModelScoped
        fun provideRetrofit() = Retrofit.Builder()
            .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
            .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json")!!))
            .build().create(AmphibiansApiService::class.java)
    }
}