package com.gkuziel.mskprep.model

import com.google.gson.annotations.SerializedName

class Event(
    val id: String,
    @SerializedName("desc")
    val description: String,
    @SerializedName("sync")
    val synchronized: Boolean = true,   // boolean indicating whether the event has been synchronised with the back end. Default value is true.
    val updated: Long? = null, // date/time indicating when a change has been made to the event OR any of its results. Default value is null meaning “never”.
    val validity: Int?, // - integer indicating the number of seconds to decay. If zero it means that the event has decayed. If null the event does not decay.
    val results: MutableList<Result>
)
