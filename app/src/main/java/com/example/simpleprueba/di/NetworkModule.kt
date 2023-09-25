package com.example.simpleprueba.di

import com.example.simpleprueba.core.repository.networks.PictureRepository
import com.example.simpleprueba.data.dto.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePictureRepository(retrofit: Retrofit): PictureRepository {
        return retrofit.create(PictureRepository::class.java)
    }
}