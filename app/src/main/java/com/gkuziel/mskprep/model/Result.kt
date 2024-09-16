package com.gkuziel.mskprep.model

import com.google.gson.annotations.SerializedName


class Result(
    val id: String,     //    id - uniquely identifies the result
    @SerializedName("desc")
    val description: String,   //    desc - string describing the result
    val type: String,     //    type - enum with values “AUTO” and “MANUAL” (case insensitive)
    val value: Int?     //    value? - integer indicating the value of the result
)

