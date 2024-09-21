package com.gkuziel.core.presentation.main


data class UIState(
    val events: List<EventUi> = emptyList(),
    val check: Int = 0
)