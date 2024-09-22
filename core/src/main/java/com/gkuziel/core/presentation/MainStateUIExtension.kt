package com.gkuziel.core.presentation

import com.gkuziel.core.presentation.details.DetailsStateUI
import com.gkuziel.core.presentation.details.ResultUI
import com.gkuziel.core.presentation.main.MainStateUI
import com.gkuziel.core.util.Util


fun MainStateUI.decrementValidity() {
    with(events) {
        filter {
            it.initValidity != null
        }.forEach {
            if (it.timeLeftToDecay!! == 0) {
                it.updateFontColor()
                it.updateClickable()
            } else {
                it.timeLeftToDecay = it.timeLeftToDecay!! - 1
            }
        }
    }
}

fun MainStateUI.updateResultValue(
    eventId: String?,
    resultId: String,
    value: Int
) {
    with(events) {
        first {
            it.id == eventId
        }.also { event ->
            event.results.firstOrNull {
                it.id == resultId
            }?.value = value
            event.markUpdated()
        }
    }
}

fun MainStateUI.addResult(
    eventId: String,
    resultId: String,
    description: String,
    value: Int
) {
    with(events) {
        first {
            it.id == eventId
        }.also { event ->
            event.results.add(
                ResultUI(resultId, description, "MANUAL", value)
            )
            event.markUpdated()
        }
    }
}

fun MainStateUI.toDetailsStateUI(eventId: String): DetailsStateUI {
    val event = this.events
        .first { it.id == eventId }
        .also {
            it.results.removeAll {
                it.type != "MANUAL"
            }
        }
    return DetailsStateUI(
        event.clickable,
        event.id,
        event.timeLeftToDecay,
        event.results,
        Util.check
    )
}
