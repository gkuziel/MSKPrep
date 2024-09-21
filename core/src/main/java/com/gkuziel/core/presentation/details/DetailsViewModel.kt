package com.gkuziel.core.presentation.details


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkuziel.core.domain.usecase.AddResultToCache
import com.gkuziel.core.domain.usecase.GetCachedEvents
import com.gkuziel.core.domain.usecase.UpdateResultValue
import com.gkuziel.core.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getCachedEvents: GetCachedEvents,
    private val addResultToCache: AddResultToCache,
    private val updateResultValue: UpdateResultValue,
) : ViewModel() {

    private lateinit var eventId: String

    val event: StateFlow<DetailsUIState> =
        getCachedEvents.execute().map {
            val event = it.events.first { it.id == eventId }
            event.results.removeAll { it.type != "MANUAL" }
            DetailsUIState(event, Util.check)
        }.stateIn(
            initialValue = DetailsUIState(),
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
        )

    fun setId(id: String) {
        this.eventId = id
    }

    fun setResultValue(
        resultId: String,
        value: Int
    ) {
        viewModelScope.launch {
            updateResultValue.execute(eventId, resultId, value)
        }
    }

    fun addResult(
        id: String,
        desc: String,
        value: Int
    ) {
        viewModelScope.launch {
            addResultToCache.execute(this@DetailsViewModel.eventId, id, desc, value)
        }
    }
}


