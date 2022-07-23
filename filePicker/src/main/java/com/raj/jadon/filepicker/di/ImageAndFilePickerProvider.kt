package com.raj.jadon.filepicker.di

import com.raj.jadon.filepicker.imageAndFilePicker.ImageAndFilePicker
import com.raj.jadon.filepicker.imageAndFilePicker.contract.ImageAndFilePickerContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ImageAndFilePickerProvider {
    @Singleton
    @Binds
    abstract fun provideImageAndFilePicker(imageAndFilePicker: ImageAndFilePicker): ImageAndFilePickerContract
}
