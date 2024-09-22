package com.gkuziel.core.domain.mapper

import com.gkuziel.core.domain.Event
import com.gkuziel.core.presentation.main.EventUI
import com.gkuziel.core.presentation.details.ResultUI
import com.gkuziel.core.presentation.main.MainStateUI
import com.gkuziel.core.presentation.updateClickable
import com.gkuziel.core.presentation.updateFontColor
import com.gkuziel.core.util.Util
import javax.inject.Inject

class EventToMainStateUi @Inject constructor() {

    fun execute(events: List<Event>): MainStateUI {
        return MainStateUI(
            events.map {
                EventUI(
                    id = it.id,
                    description = it.description,
                    synchronized = it.synchronized,
                    updated = it.updated,
                    timeLeftToDecay = it.validity,
                    initValidity = it.validity,
                    results = it.results.map {
                        ResultUI(
                            it.id,
                            it.description,
                            it.type,
                            it.value
                        )
                    }.toMutableList(),
                ).also {
                    it.updateFontColor()
                    it.updateClickable()
                }
            },
            Util.check
        )
    }
}