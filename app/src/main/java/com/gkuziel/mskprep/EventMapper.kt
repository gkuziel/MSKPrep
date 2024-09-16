package com.gkuziel.mskprep

import android.graphics.Color
import com.gkuziel.mskprep.presentation.main.EventUi
import com.gkuziel.mskprep.presentation.details.ResultUi
import com.gkuziel.mskprep.domain.Event
import com.gkuziel.mskprep.presentation.main.UIState
import com.gkuziel.mskprep.util.Util
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