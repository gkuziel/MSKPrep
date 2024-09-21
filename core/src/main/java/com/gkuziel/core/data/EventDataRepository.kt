package com.gkuziel.core.data

import android.util.Log
import com.gkuziel.core.EventToMainStateUi
import com.gkuziel.core.domain.EventRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EventDataRepository @Inject constructor(
    private val eventCache: EventCache,
    private val fakeRemoteRepository: FakeRemoteRepository,
    private val eventToMainStateUi: EventToMainStateUi, // tutaj?
    private val dynamicUpdate: DynamicUpdate
) : EventRepository {
    override suspend fun loadUsers() {
        fakeRemoteRepository.getEventsFlow()
            .map {
                eventToMainStateUi.execute(it)
            }
            .collect {
                eventCache.setList(it)


//                 not here! app?
                dynamicUpdate.get {
                    findMaximalTimeToDecay()
                }.collect {
                    eventCache.tick()
                    Log.d("sfsfds", "tick")
                }



            }
    }

    override fun getCachedEvents() = eventCache.cacheFlow

    override suspend fun updateResult(
        eventId: String?,
        resultId: String,
        value: Int
    ) {
        eventCache.updateResultValue(eventId, resultId, value)
    }


    override suspend fun addResult(
        eventId: String,
        id: String,
        desc: String,
        value: Int
    ) {
        eventCache.addResult(eventId, id, desc, value)
    }


    private fun findMaximalTimeToDecay(): Int {
        return getCachedEvents().value.events.maxByOrNull {
            it.timeLeftToDecay ?: 0
        }?.timeLeftToDecay ?: 0
    }

}