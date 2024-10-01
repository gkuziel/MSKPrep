package com.gkuziel.core.di


import com.gkuziel.core.domain.usecase.LoadEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppCore @Inject constructor() {

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    @Inject
    lateinit var loadEvents: LoadEvents

    fun onCreate() {
        applicationScope.launch {
            loadEvents.execute()
        }
    }

    fun onTerminate() {
        applicationScope.cancel()
    }
}