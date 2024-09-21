package com.gkuziel.core.di

import com.gkuziel.core.data.EventDataRepository
import com.gkuziel.core.domain.EventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provide(eventDataRepository: EventDataRepository): EventRepository = eventDataRepository
}