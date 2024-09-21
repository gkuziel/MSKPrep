package com.gkuziel.core.data

import com.gkuziel.core.domain.Event
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    fun getEventsFlow(): Flow<List<Event>>
}