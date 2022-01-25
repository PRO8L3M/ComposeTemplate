package com.example.data.di

import com.example.data.services.UsersService
import com.example.data.services.api.DefaultUsersService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ServicesModule {

    @Binds
    fun bindUsersApiService(service: DefaultUsersService): UsersService
}
