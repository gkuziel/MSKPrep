package com.gkuziel.core.data

import android.graphics.Color
import com.gkuziel.core.presentation.details.ResultUI
import com.gkuziel.core.presentation.main.EventUI
import com.gkuziel.core.presentation.main.MainStateUI
import com.gkuziel.core.util.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class EventCache @Inject constructor() {

    private val _cacheFlow = MutableStateFlow(MainStateUI())
    val cacheFlow: StateFlow<MainStateUI> get() = _cacheFlow

    fun setList(list: MainStateUI) {
        _cacheFlow.value = list
    }

    // separate sth
    suspend fun updateResultValue(
        eventId: String?,
        resultId: String,
        value: Int
    ) {
        // with context?
        val updatedEvents = cacheFlow.value.events.toList()

        val updatedEvent = updatedEvents.first {
            it.id == eventId
        }
        updatedEvent.results.firstOrNull {
            it.id == resultId
        }?.value = value

        updatedEvent.markUpdated()

        _cacheFlow.value = _cacheFlow.value.copy(
            events = updatedEvents,
            check = Util.check
        )
    }


    private fun isDecayed(event: EventUI) = event.timeLeftToDecay == 0

    private fun getFontColor(event: EventUI) = when {
        isDecayed(event) -> Color.RED
        event.updated != null -> Color.GREEN
        else -> Color.BLACK
    }

    suspend fun addResult(eventId: String, id: String, desc: String, value: Int) {
        with(Dispatchers.Default) {

            val updatedEvents = cacheFlow.value.events.toMutableList()

            val updatedEvent = updatedEvents.first {
                it.id == eventId
            }
            updatedEvent.results.add(
                ResultUI(id, desc, "MANUAL", value)

            )

            updatedEvent.markUpdated()

            _cacheFlow.value = _cacheFlow.value.copy(
                events = updatedEvents,
                check = Util.check
            )
        }
    }

    private fun EventUI.markUpdated() {
        updated = 100L
        fontColor = getFontColor(this)
        clickable = !isDecayed(this)
        synchronized = false
        timeLeftToDecay = initValidity
    }

    fun tick() {
        val updatedEvents = cacheFlow.value.events.toMutableList()

        updatedEvents
            .filter { it.initValidity != null }
            .forEach {
                if (it.timeLeftToDecay!! == 0) {
                    // update
                    it.fontColor = getFontColor(it)
                    it.clickable = !isDecayed(it)
                } else {
                    it.timeLeftToDecay = it.timeLeftToDecay!! - 1
                }
            }

        _cacheFlow.value = _cacheFlow.value.copy(
            events = updatedEvents,
            check = Util.check
        )
    }

}
