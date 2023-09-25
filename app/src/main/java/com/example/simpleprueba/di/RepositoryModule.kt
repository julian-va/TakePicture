package com.example.simpleprueba.di

import com.example.simpleprueba.core.usecase.PictureService
import com.example.simpleprueba.core.usecase.impl.PictureServicerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
   
    @Binds
    abstract fun providePictureService(impl: PictureServicerImpl): PictureService
}