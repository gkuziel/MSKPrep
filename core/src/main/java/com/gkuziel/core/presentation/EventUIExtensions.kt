package com.gkuziel.core.presentation

import android.graphics.Color
import com.gkuziel.core.presentation.main.EventUI
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun EventUI.markUpdated() {
    updated = getUpdatedForDisplay(Date().time)
    synchronized = false
    timeLeftToDecay = initValidity
    updateFontColor()
    updateClickable()
}

fun getUpdatedForDisplay(updated: Long?) = updated?.let {
    SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(updated)
}

fun EventUI.updateFontColor() {
    fontColor = getFontColor(this)
}

fun EventUI.updateClickable() {
    clickable = !updateClickable(this)
}

private fun getFontColor(event: EventUI) = when {
    updateClickable(event) -> Color.RED
    event.updated != null -> Color.GREEN
    else -> Color.BLACK
}

private fun updateClickable(event: EventUI) = event.timeLeftToDecay == 0
