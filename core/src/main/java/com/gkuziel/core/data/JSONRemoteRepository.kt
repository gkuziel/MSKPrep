package com.gkuziel.core.data

import android.content.Context
import com.gkuziel.core.R
import com.gkuziel.core.data.model.Event
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class JSONRemoteRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val jsonParser: JSONParser
) : RemoteRepository {

    override fun getEventsFlow(): Flow<List<Event>> {
        return flow {
            val jsonString = loadFile()
            val events = jsonParser.parse(jsonString)
            emit(events)
        }
    }


    private suspend fun loadFile(): String {
        return withContext(Dispatchers.Default) {
            val inputStream = context.resources.openRawResource(R.raw.sample_from_mail)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val jsonString = reader.readText()
            reader.close()
            jsonString
        }
    }
}