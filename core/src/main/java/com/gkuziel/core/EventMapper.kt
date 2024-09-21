package com.gkuziel.core

import android.graphics.Color
import com.gkuziel.core.presentation.main.EventUi
import com.gkuziel.core.presentation.details.ResultUi
import com.gkuziel.core.domain.Event
import com.gkuziel.core.presentation.main.UIState
import com.gkuziel.core.util.Util
import javax.inject.Inject

class EventMapper @Inject constructor() {

    fun execute(events: List<Event>): UIState {
//    fun execute(events: List<Event>): List<EventUi> {
        return UIState(
            events.map {
                EventUi(
                    getFontColor(it),
                    !isDecayed(it),
                    it.id,
                    it.description,
                    it.synchronized,
                    it.updated,
                    it.validity,
                    it.validity,
                    it.results.map {
                        ResultUi(
                            it.id,
                            it.description,
                            it.type,
                            it.value
                        )
                    }.toMutableList(),
                )
            },
//            events.check
            Util.check
        )
    }


    private fun isDecayed(event: Event) = event.validity == 0

    private fun getFontColor(event: Event) = when {
        isDecayed(event) -> Color.RED
        event.updated != null -> Color.GREEN
        else -> Color.BLACK
    }
}