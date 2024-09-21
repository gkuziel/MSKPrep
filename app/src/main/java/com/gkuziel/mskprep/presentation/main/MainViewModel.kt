package com.gkuziel.mskprep.presentation.main


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkuziel.mskprep.domain.usecase.GetCachedEvents
import com.gkuziel.mskprep.domain.usecase.LoadUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCachedEvents: GetCachedEvents,
    private val loadUsers: LoadUsers
) : ViewModel() {

    val events: StateFlow<UIState> =
        getCachedEvents.execute()
            .stateIn(
                initialValue = UIState(),
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
            )

    fun loadUsers() {
        viewModelScope.launch {
            Log.d("sffsd","ttrw")
            loadUsers.execute()
        }
    }
}


