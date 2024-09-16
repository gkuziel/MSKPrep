package com.gkuziel.mskprep.presentation.details

import com.gkuziel.mskprep.presentation.main.EventUi


data class DetailsUIState(
    val event: EventUi? = null,
    val check: Int = 0
)