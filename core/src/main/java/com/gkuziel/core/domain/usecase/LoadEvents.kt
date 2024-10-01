package com.gkuziel.core.domain.usecase


import com.gkuziel.core.domain.EventRepository
import javax.inject.Inject


class LoadEvents @Inject constructor(
    private val repository: EventRepository
) {
    suspend fun execute() {
        return repository.loadEvents()
    }
}


