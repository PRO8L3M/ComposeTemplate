package com.example.data.di

import com.example.data.networking.endpoints.UsersEndpoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object EndpointsModule {

    @Provides
    fun getUsersEndpoint(retrofit: Retrofit): UsersEndpoints {
        return retrofit.create(UsersEndpoints::class.java)
    }
}