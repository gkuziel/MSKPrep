package com.gkuziel.mskprep.presentation.main

import com.gkuziel.mskprep.presentation.details.ResultUi


data class EventUi(
    var fontColor: Int,
    var clickable: Boolean,
    val id: String,
    var description: String,
    var synchronized: Boolean = true,
    var updated: Long? = null,
    var timeLeftToDecay: Int?,
    val initValidity: Int?,
    val results: MutableList<ResultUi>,
)
