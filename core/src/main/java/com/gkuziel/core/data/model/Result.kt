package com.gkuziel.core.data.model

import com.google.gson.annotations.SerializedName


data class Result(
    val id: String,
    @SerializedName("desc")
    val description: String,
    val type: String,
    var value: Int?
)

