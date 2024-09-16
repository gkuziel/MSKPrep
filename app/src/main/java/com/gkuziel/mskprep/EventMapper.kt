package com.gkuziel.mskprep

import android.graphics.Color
import com.gkuziel.mskprep.presentation.EventUi
import com.gkuziel.mskprep.presentation.ResultUi
import com.gkuziel.mskprep.model.Event
import javax.inject.Inject

class  EventMapper  @Inject constructor() {

    fun execute(events: List<Event>): List<EventUi> {
        return events.map {
            EventUi(
                getFontColor(it),
                !isDecayed(it),
                it.id,
                it.description,
                it.synchronized,
                it.updated,
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
        }
    }

    private fun isDecayed(event: Event) = event.validity == 0

    private fun getFontColor(event: Event) = when {
        isDecayed(event) -> Color.RED
        event.updated != null -> Color.GREEN
        else -> Color.BLACK
    }
}