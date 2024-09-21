package com.gkuziel.core

import com.gkuziel.core.presentation.details.DetailsStateUI
import com.gkuziel.core.presentation.main.MainStateUI
import com.gkuziel.core.util.Util
import javax.inject.Inject

class MainStateUIToDetailsStateUI @Inject constructor() {

    fun execute(
        mainStateUI: MainStateUI,
        eventId: String
    ): DetailsStateUI {
        val event = mainStateUI.events
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
}