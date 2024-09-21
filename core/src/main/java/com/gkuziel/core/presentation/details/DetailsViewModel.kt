package com.gkuziel.core.presentation.details


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkuziel.core.MainStateUIToDetailsStateUI
import com.gkuziel.core.domain.usecase.AddResultToCache
import com.gkuziel.core.domain.usecase.GetCachedEvents
import com.gkuziel.core.domain.usecase.UpdateResultValue
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
//    private val getCachedEventByID: GetCachedEventByID,
    private val mainStateUIToDetailsStateUI: MainStateUIToDetailsStateUI,   // NOT HERE
) : ViewModel() {

    private lateinit var eventId: String

    //    private var eventId: String? = null
    val event: StateFlow<DetailsStateUI?> = getCachedEvents.execute().map {
        mainStateUIToDetailsStateUI.execute(
            it,
            eventId
        )
    }.stateIn(
        initialValue = null,
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
    )

    fun setId(id: String) {
        eventId = id
    }

    fun setResultValue(
        resultId: String,
        value: Int
    ) {
        viewModelScope.launch {
            updateResultValue.execute(
                eventId!!,
                resultId,
                value
            )
        }
    }

    fun addResult(
        id: String,
        desc: String,
        value: Int
    ) {
        viewModelScope.launch {
            addResultToCache.execute(
                this@DetailsViewModel.eventId,
                id,
                desc,
                value
            )
        }
    }
}


