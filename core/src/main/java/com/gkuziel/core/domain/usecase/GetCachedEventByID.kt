package com.gkuziel.core.domain.usecase


import com.gkuziel.core.domain.mapper.MainStateUIToDetailsStateUI
import com.gkuziel.core.domain.EventRepository
import com.gkuziel.core.presentation.details.DetailsStateUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetCachedEventByID @Inject constructor(
    private val repository: EventRepository,
    private val mainStateUIToDetailsStateUI: MainStateUIToDetailsStateUI
) {
    fun execute(eventId: String): Flow<DetailsStateUI> {
        return repository.getCachedEvents().map {
            mainStateUIToDetailsStateUI.execute(
                it,
                eventId
            )
        }
    }
}


