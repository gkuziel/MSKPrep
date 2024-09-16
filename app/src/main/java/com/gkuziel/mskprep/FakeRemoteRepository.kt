package com.gkuziel.mskprep

import android.content.res.Resources
import com.gkuziel.mskprep.model.Event
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.BufferedReader
import java.io.InputStreamReader

//inject
class FakeRemoteRepository(
    // inject
    private val resources: Resources
) {

    // suspend
    fun getEvents(): List<Event> {
        val jsonString = loadFile()
        val events = parse(jsonString)
        return events
    }

    fun getEventsFlow(): Flow<List<Event>> {
        return flow {
            val jsonString = loadFile()
            val events = parse(jsonString)
            emit(events)
        }
    }

    // suspend
    private fun parse(jsonString: String): List<Event> {
        val gson = Gson()
        val listType = object : TypeToken<List<Event>>() {}.type
        val personList: List<Event> = gson.fromJson(jsonString, listType)
        return personList
    }

    // suspend
    private fun loadFile(): String {
        val inputStream = resources.openRawResource(R.raw.sample_from_mail)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val jsonString = reader.readText()
        reader.close()
        return jsonString
    }
}