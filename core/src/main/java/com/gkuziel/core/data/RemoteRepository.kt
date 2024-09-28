package com.gkuziel.core.data

import com.gkuziel.core.data.model.Event
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    fun getEventsFlow(): Flow<List<Event>>
}