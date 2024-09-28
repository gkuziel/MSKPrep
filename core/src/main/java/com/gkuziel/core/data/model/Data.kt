package com.gkuziel.core.data.model

import com.gkuziel.core.util.Util


data class Data(
    val events: List<Event> = emptyList(),
    private val check: Int = Util.check
)