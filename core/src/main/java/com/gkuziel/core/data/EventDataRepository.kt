package com.gkuziel.core.data

import com.gkuziel.core.domain.mapper.EventToMainStateUi
import com.gkuziel.core.domain.EventRepository
import com.gkuziel.core.updateservice.DynamicUpdate
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EventDataRepository @Inject constructor(
    private val eventCache: EventCache,
    private val remoteRepository: RemoteRepository,
    private val eventToMainStateUi: EventToMainStateUi,
    private val dynamicUpdate: DynamicUpdate
) : EventRepository {
    override suspend fun loadUsers() {
        remoteRepository.getEventsFlow()
            .map {
                eventToMainStateUi.execute(it)
            }
            .collect {
                eventCache.setList(it)


//                 not here! app?
                dynamicUpdate.get {
                    findMaxTimeToDecay()
                }.collect {
                    eventCache.triggerCountdownUpdate()
                }


            }
    }

    override fun getCachedEvents() = eventCache.cacheFlow

    override suspend fun updateResult(
        eventId: String?,
        resultId: String,
        value: Int
    ) {
        eventCache.updateResultValue(
            eventId,
            resultId,
            value
        )
    }

    override suspend fun addResult(
        eventId: String,
        resultId: String,
        description: String,
        value: Int
    ) {
        eventCache.addResult(
            eventId,
            resultId,
            description,
            value
        )
    }


    private fun findMaxTimeToDecay(): Int {
        return getCachedEvents().value.events.maxByOrNull {
            it.timeLeftToDecay ?: 0
        }?.timeLeftToDecay ?: 0
    }

}