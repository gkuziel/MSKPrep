package com.gkuziel.mskprep.presentation.main

import com.gkuziel.mskprep.presentation.main.EventUi


data class UIState(
    val events: List<EventUi> = emptyList(),
    val check: Int = 0
)