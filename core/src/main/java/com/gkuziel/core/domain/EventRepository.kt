package com.gkuziel.core.domain

import com.gkuziel.core.presentation.main.MainStateUI
import kotlinx.coroutines.flow.Flow


interface EventRepository {

    suspend fun loadUsers()

    fun getCachedEvents(): Flow<MainStateUI>

    suspend fun updateResult(
        eventId: String?,
        resultId: String,
        value: Int
    )

    suspend fun addResult(
        eventId: String,
        resultId: String,
        description: String,
        value: Int
    )
}