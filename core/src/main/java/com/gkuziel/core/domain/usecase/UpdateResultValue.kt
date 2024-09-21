package com.gkuziel.core.domain.usecase


import com.gkuziel.core.domain.EventRepository
import javax.inject.Inject


class UpdateResultValue @Inject constructor(
    private val repository: EventRepository
) {
    suspend fun execute(
        eventId: String,
        resultId: String,
        value: Int
    ) {
        repository.updateResult(
            eventId,
            resultId,
            value
        )
    }
}


