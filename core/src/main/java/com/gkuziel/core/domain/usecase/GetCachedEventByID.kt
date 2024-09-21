package com.gkuziel.core.domain.usecase


import com.gkuziel.core.data.Repository
import com.gkuziel.core.presentation.details.DetailsStateUI
import com.gkuziel.core.presentation.main.MainStateUI
import com.gkuziel.core.util.Util
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetCachedEventByID @Inject constructor(
    private val repository: Repository
) {
//    fun execute(eventId: String?): Flow<DetailsStateUI> {
//        return repository.getCachedEvents().map {
////            mapper.execute(it.events)
//
//            val event = it.events.firstOrNull { it.id == eventId }
//            event?.results?.removeAll { it.type != "MANUAL" }
//
//
//
//            DetailsStateUI(event, Util.check)
//
//
////            it
//        }

//    }
}


