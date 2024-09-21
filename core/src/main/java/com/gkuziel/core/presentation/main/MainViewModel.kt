package com.gkuziel.core.presentation.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkuziel.core.domain.usecase.GetCachedEvents
import com.gkuziel.core.domain.usecase.LoadUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCachedEvents: GetCachedEvents,
    private val loadUsers: LoadUsers,
) : ViewModel() {

    val events: StateFlow<MainStateUI> =
        getCachedEvents.execute()
            .stateIn(
                initialValue = MainStateUI(),
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
            )

    fun loadUsers() {
        viewModelScope.launch {
            loadUsers.execute()
        }
    }
}


