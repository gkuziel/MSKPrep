package com.gkuziel.core.data

import com.gkuziel.core.presentation.addResult
import com.gkuziel.core.presentation.main.MainStateUI
import com.gkuziel.core.presentation.decrementValidity
import com.gkuziel.core.presentation.updateResultValue
import com.gkuziel.core.util.Util
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class EventCache @Inject constructor() {

//    cache chyba powinien trzymac cos innego, a nie MainStateUI
// DTO's?

    private val _cacheFlow = MutableStateFlow(MainStateUI())
    val cacheFlow: StateFlow<MainStateUI> get() = _cacheFlow

    fun setList(list: MainStateUI) {
        _cacheFlow.value = list
    }

    fun updateResultValue(
        eventId: String?,
        resultId: String,
        value: Int
    ) {
        cacheFlow.value.updateResultValue(eventId, resultId, value)
        forceFlowUpdate()
    }

    fun addResult(
        eventId: String,
        resultId: String,
        description: String,
        value: Int
    ) {
        cacheFlow.value.addResult(eventId, resultId, description, value)
        forceFlowUpdate()
    }

    fun triggerCountdownUpdate() {
        cacheFlow.value.decrementValidity()
        forceFlowUpdate()
    }

    private fun forceFlowUpdate() {
        _cacheFlow.value = _cacheFlow.value.copy(
            check = Util.check
        )
    }
}
