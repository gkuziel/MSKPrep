package com.gkuziel.core.presentation

import com.gkuziel.core.presentation.details.DetailsStateUI


fun DetailsStateUI.getDecayInfoLabel(): String {
    return if (!isDecayable()) {
        "The Event never decays"
    } else if (isClickable()) {
        "Decays in: " + timeLeftToDecay.toString()
    } else {
        "Decayed - read only"
    }
}

private fun DetailsStateUI.isDecayable() = timeLeftToDecay != null

private fun DetailsStateUI.isClickable() = editable




