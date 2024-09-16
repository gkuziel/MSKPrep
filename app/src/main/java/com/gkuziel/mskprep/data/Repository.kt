package com.gkuziel.mskprep.data

import com.gkuziel.mskprep.EventMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(
    private val eventCache: EventCache,
    private val fakeRemoteRepository: FakeRemoteRepository,
    private val eventMapper: EventMapper,
    private val timer: Timer
) {
    suspend fun loadUsers() {
        fakeRemoteRepository.getEventsFlow().map {
            it
        }.collect {
            eventCache.setList(
                eventMapper.execute(it)
            )
            timer.get().collect()
        }
    }

    fun getCachedEvents() = eventCache.cacheFlow

    suspend fun updateResult(
        eventId: String?,
        resultId: String,
        value: Int
    ) {
        eventCache.updateResultValue(eventId, resultId, value)
    }

    suspend fun addResult(
        eventId: String,
        id: String,
        desc: String,
        value: Int
    ) {
        eventCache.addResult(eventId, id, desc, value)
    }
}