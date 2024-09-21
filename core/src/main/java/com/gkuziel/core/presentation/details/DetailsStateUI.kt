package com.gkuziel.core.presentation.details


data class DetailsStateUI(
    var editable: Boolean,
    val eventId: String,
    var timeLeftToDecay: Int?,
    val results: MutableList<ResultUI>,
    private val check: Int = 0
)