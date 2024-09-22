package com.gkuziel.core.presentation

import android.graphics.Color
import com.gkuziel.core.presentation.main.EventUI


fun EventUI.markUpdated() {
    updated = 100L
    synchronized = false
    timeLeftToDecay = initValidity
    updateFontColor()
    updateClickable()
}

fun EventUI.updateFontColor() {
    fontColor = getFontColor(this)
}

fun EventUI.updateClickable() {
    clickable = !updateClickable(this)
}

fun updateClickable(event: EventUI) = event.timeLeftToDecay == 0

fun getFontColor(event: EventUI) = when {
    updateClickable(event) -> Color.RED
    event.updated != null -> Color.GREEN
    else -> Color.BLACK
}