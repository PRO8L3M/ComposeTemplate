package com.example.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        RetrofitModule::class,
        RepositoriesModule::class,
        ServicesModule::class,
        EndpointsModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
interface DataModule