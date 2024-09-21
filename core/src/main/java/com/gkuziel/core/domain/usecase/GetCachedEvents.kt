package com.gkuziel.core.domain.usecase


import com.gkuziel.core.domain.EventRepository
import com.gkuziel.core.presentation.main.MainStateUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetCachedEvents @Inject constructor(
    private val repository: EventRepository
) {
    fun execute(): Flow<MainStateUI> {
        return repository.getCachedEvents().map {
//            mapper.execute(it.events)
            it
        }
    }
}


