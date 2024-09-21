package com.gkuziel.core.presentation.details


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkuziel.core.domain.usecase.AddResultToCache
import com.gkuziel.core.domain.usecase.GetCachedEventByID
import com.gkuziel.core.domain.usecase.UpdateResultValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val addResultToCache: AddResultToCache,
    private val updateResultValue: UpdateResultValue,
    private val getCachedEventByID: GetCachedEventByID,
) : ViewModel() {

    private val eventId = MutableStateFlow<String?>(null)

    val eventDetailsState: StateFlow<DetailsStateUI?> = eventId
        .filterNotNull()
        .flatMapLatest { id ->
            getCachedEventByID.execute(id)
        }.stateIn(
            initialValue = null,
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
        )

    fun setEventId(id: String) {
        eventId.value = id
    }

    fun updateResultValue(
        resultId: String,
        value: Int
    ) {
        viewModelScope.launch {

            updateResultValue.execute(
                eventId.value!!,
                resultId,
                value
            )
        }
    }

    fun addResultToEvent(
        description: String,
        value: Int
    ) {
        viewModelScope.launch {
            addResultToCache.execute(
                eventId.value!!,
                description,
                value
            )
        }
    }
}


