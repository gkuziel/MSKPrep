package com.gkuziel.core.data

import android.util.Log
import com.gkuziel.core.EventToMainStateUi
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(
    private val eventCache: EventCache,
    private val fakeRemoteRepository: FakeRemoteRepository,
    private val eventToMainStateUi: EventToMainStateUi, // tutaj?
    private val dynamicUpdate: DynamicUpdate
) {
    suspend fun loadUsers() {
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

    private fun findMaximalTimeToDecay(): Int {
        return getCachedEvents().value.events.maxByOrNull {
            it.timeLeftToDecay ?: 0
        }?.timeLeftToDecay ?: 0
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