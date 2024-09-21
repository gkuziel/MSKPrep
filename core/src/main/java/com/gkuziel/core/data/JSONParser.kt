package com.gkuziel.core.data

import com.gkuziel.core.domain.Event
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JSONParser @Inject constructor() {

    suspend fun parse(jsonString: String): List<Event> {
        return withContext(Dispatchers.Default) {
            val gson = Gson()
            val events = try {
                val listType = object : TypeToken<List<Event>>() {}.type
                val eventList = gson.fromJson<MutableList<Event>>(jsonString, listType)
                eventList.removeAll { event ->
                    try {
                        requireNotNull(event.id)
                        requireNotNull(event.description)
                        requireNotNull(event.synchronized)
                        event.results.removeAll { result ->
                            try {
                                requireNotNull(result.id)
                                requireNotNull(result.description)
                                requireNotNull(result.type)
                                false
                            } catch (e: IllegalArgumentException) {
                                true
                            }
                        }
                        false
                    } catch (e: IllegalArgumentException) {
                        true
                    }
                }
                eventList
            } catch (e: JsonSyntaxException) {
                emptyList()
            }
            events
        }
    }
}