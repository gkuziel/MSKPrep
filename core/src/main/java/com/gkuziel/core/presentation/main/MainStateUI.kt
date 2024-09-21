package com.gkuziel.core.presentation.main


data class MainStateUI(
    val events: List<EventUI> = emptyList(),
    private val check: Int = 0
)