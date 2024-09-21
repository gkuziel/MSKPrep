package com.gkuziel.core.domain.usecase


import com.gkuziel.core.domain.EventRepository
import javax.inject.Inject


class AddResultToCache @Inject constructor(
    private val repository: EventRepository
) {
    suspend fun execute(eventId: String, id: String, desc: String, value: Int) {
        repository.addResult(eventId, id, desc, value)
    }
}


