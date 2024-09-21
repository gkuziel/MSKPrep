package com.gkuziel.core.presentation.details

import com.gkuziel.core.presentation.main.EventUi


data class DetailsUIState(
    val event: EventUi? = null,
    val check: Int = 0
)