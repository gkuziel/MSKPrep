package com.gkuziel.core.domain

import com.google.gson.annotations.SerializedName

data class Event(
    val id: String,
    @SerializedName("desc")
    val description: String,
    @SerializedName("sync")
    val synchronized: Boolean = true,
    val updated: Long? = null,
    val validity: Int?,
    val results: MutableList<Result>
)
