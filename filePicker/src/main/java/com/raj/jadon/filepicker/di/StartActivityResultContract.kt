package com.raj.jadon.filepicker.di

import com.raj.jadon.filepicker.customStartActivityResult.StartActivityContracts
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityResultContractListenerImplement
import com.raj.jadon.filepicker.customStartActivityResult.StartActivityResultCustomContract
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StartActivityResultContractProvider {

    @Singleton
    @Binds
    abstract fun provideStartActivityContract(startActivityContract: StartActivityResultContractListenerImplement): StartActivityContracts
}


@Module
@InstallIn(SingletonComponent::class)
object StartActivityResultProvider {

    @Singleton
    @Provides
    fun providesActivityResult(startActivityContracts: StartActivityContracts) =
        StartActivityResultCustomContract(startActivityContracts)
}
