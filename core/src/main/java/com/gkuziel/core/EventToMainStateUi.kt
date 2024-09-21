package com.gkuziel.core

import android.graphics.Color
import com.gkuziel.core.presentation.main.EventUI
import com.gkuziel.core.presentation.details.ResultUI
import com.gkuziel.core.domain.Event
import com.gkuziel.core.presentation.main.MainStateUI
import com.gkuziel.core.util.Util
import javax.inject.Inject

class EventToMainStateUi @Inject constructor() {

    fun execute(events: List<Event>): MainStateUI {
        return MainStateUI(
            events.map {
                EventUI(
                    getFontColor(it),
                    !isDecayed(it),
                    it.id,
                    it.description,
                    it.synchronized,
                    it.updated,
                    it.validity,
                    it.validity,
                    it.results.map {
                        ResultUI(
                            it.id,
                            it.description,
                            it.type,
                            it.value
                        )
                    }.toMutableList(),
                )
            },
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