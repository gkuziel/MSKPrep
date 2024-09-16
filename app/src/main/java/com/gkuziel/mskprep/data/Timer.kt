package com.gkuziel.mskprep.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Timer @Inject constructor(
    private val eventCache: EventCache,
) {

    fun get(): Flow<Void> {
        return flow {
            while (true) {
                val max = findMaxRemainingTime()
                delay(1000)
                eventCache.tick()
                if (max == 0) {
                    break
                }
            }
        }
    }

    private fun findMaxRemainingTime() =
        getCachedEvents().value.events.maxByOrNull { it.timeLeftToDecay ?: 0 }?.timeLeftToDecay
            ?: 0

    private fun getCachedEvents() = eventCache.cacheFlow

}