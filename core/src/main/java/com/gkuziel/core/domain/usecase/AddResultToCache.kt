package com.gkuziel.core.domain.usecase


import com.gkuziel.core.domain.EventRepository
import java.util.UUID
import javax.inject.Inject


class AddResultToCache @Inject constructor(
    private val repository: EventRepository
) {
    suspend fun execute(
        eventId: String,
        description: String,
        value: Int
    ) {
        repository.addResult(
            eventId,
            UUID.randomUUID().toString(),
            description,
            value
        )
    }
}


