package com.gkuziel.core.presentation.main

import com.gkuziel.core.presentation.details.ResultUI


data class EventUI(
    var fontColor: Int,                 // needed on UI
    var clickable: Boolean,             // needed on UI
    val id: String,                     // needed on UI
    var description: String,            // needed on UI
    var synchronized: Boolean = true,   // needed on UI
    var updated: Long? = null,          // needed on UI
    var timeLeftToDecay: Int?,          // needed on UI
    val initValidity: Int?,             // NOT
    val results: MutableList<ResultUI>, // needed on UI
)
