package com.gkuziel.core.presentation.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkuziel.core.domain.usecase.GetCachedEvents
import com.gkuziel.core.domain.usecase.LoadEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCachedEvents: GetCachedEvents,
    private val loadEvents: LoadEvents,
) : ViewModel() {
    private var eventsLoaded = false

    val mainState: StateFlow<MainStateUI> =
        getCachedEvents.execute()
            .stateIn(
                initialValue = MainStateUI(),
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
            )

    fun loadEvents() {
        viewModelScope.launch {
            loadEvents.execute()
        }
    }
}


