package com.gkuziel.mskprep


import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkuziel.mskprep.presentation.EventUi
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(

    @ApplicationContext private val context: Context,
//    private val repository: Repository = Repository()
//    private val resources: Resources,
    private val mapper: EventMapper = EventMapper()
) : ViewModel() {


    //     StateFlow to hold the current list of users
    private val _users = MutableStateFlow<EventUi?>(null)
//    val users: StateFlow<List<EventUi>> = _users

    val events: StateFlow<List<EventUi>> =
        FakeRemoteRepository(context.resources).getEventsFlow().map {
            mapper.execute(it)
        }.combine(_users) { list, event ->
            if (event == null) {
                list
            } else {
                list.map {
                    if (it.id == event.id) {
                        it.description = "U " + it.description
                        it
                    } else it
                }
            }

        }.stateIn(
            initialValue = emptyList(),
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
        )

//
//    val homeUiState: StateFlow<HomeUiState> =
//        itemsRepository.getAllItemsStream()
//            .map { HomeUiState(it) }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                initialValue = HomeUiState()
//            )


    // Fetch users from backend
    fun loadUsers(resources: Resources) {
//        us
//        events.value =
//        viewModelScope.launch {
//            FakeRemoteRepository(resources).getEventsFlow().map {
//                mapper.execute(it)
//            }.collect { userList ->
//                events.value = userList
//            }
//        }
    }

    // Modify user in the local list
    fun modifyUser(id: EventUi) {
        _users.value = id
//        events.value = events.value.map {
//            if (it.id == id) {
//                it.description = "U " + it.description
//                it
////                updatedUser
//            } else it
//        }
    }


    // Persist updated user to backend
    fun saveUserChanges(user: EventUi) {
        viewModelScope.launch {
            try {
//                updateUserUseCase(user)
                // Optionally show success message
            } catch (e: Exception) {
                // Handle error, e.g., show error message
            }
        }
    }


//    private val initialViewState = emptyList<RssItem>()
//    private val _state = MutableLiveData(initialViewState)
//
//    val state: LiveData<List<RssItem>>
//        get() = _state


//    fun clearData() {
//        _state.value = emptyList()
//    }
//
//    fun loadData(): Job {
//        return viewModelScope.launch {
//            try {
//                _state.value = repository.loadRssWorlds()
//            } catch (error: Throwable) {
//                // todo: add exception handling
//            } finally {
//                // todo: add exception handling
//            }
//        }
//    }
}


