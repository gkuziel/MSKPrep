package com.gkuziel.core.data.model

import com.google.gson.annotations.SerializedName

data class Event(
    val id: String,
    @SerializedName("desc")
    val description: String,
    @SerializedName("sync")
    val synchronized: Boolean = true,
    val updated: Long? = null,
    var validity: Int?,
    val initValidity: Int?,
    val results: MutableList<Result>
)
