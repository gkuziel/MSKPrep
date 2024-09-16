package com.gkuziel.mskprep.presentation


data class EventUi(
    val fontColor: Int,
    val clickable: Boolean,
    val id: String,
    var description: String,
    val synchronized: Boolean = true,
    val updated: Long? = null,
    val validity: Int?,
    val results: MutableList<ResultUi>,
)
