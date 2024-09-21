package com.gkuziel.core.domain.usecase


import com.gkuziel.core.domain.EventRepository
import javax.inject.Inject


class LoadUsers @Inject constructor(
    private val repository: EventRepository
) {
    suspend fun execute() {
        return repository.loadUsers()
    }
}


