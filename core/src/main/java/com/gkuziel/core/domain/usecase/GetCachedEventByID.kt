package com.gkuziel.core.domain.usecase


import com.gkuziel.core.domain.EventRepository
import javax.inject.Inject


class GetCachedEventByID @Inject constructor(
    private val repository: EventRepository
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


