package com.gkuziel.core.domain.mapper

import com.gkuziel.core.presentation.main.MainStateUI
import com.gkuziel.core.presentation.toDetailsStateUI
import javax.inject.Inject

class MainStateUIToDetailsStateUI @Inject constructor() {

    fun execute(
        mainStateUI: MainStateUI,
        eventId: String
    ) = mainStateUI.toDetailsStateUI(eventId)
}