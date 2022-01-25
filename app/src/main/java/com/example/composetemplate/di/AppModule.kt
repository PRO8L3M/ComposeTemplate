package com.example.composetemplate.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [DataStoreModule::class])
interface AppModule

//todo Where include DataStore Module