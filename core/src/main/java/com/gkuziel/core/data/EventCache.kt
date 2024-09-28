package com.gkuziel.core.data

import com.gkuziel.core.data.model.Data
import com.gkuziel.core.presentation.addResult
import com.gkuziel.core.presentation.main.MainStateUI
import com.gkuziel.core.presentation.decrementValidity
import com.gkuziel.core.presentation.updateClickable
import com.gkuziel.core.presentation.updateFontColor
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

    private val _cacheFlow = MutableStateFlow(Data())
    val cacheFlow: StateFlow<Data> get() = _cacheFlow

    fun setList(list: Data) {
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

        cacheFlow.value.events.filter {
            it.initValidity != null
        }.forEach {
            if (it.validity!! == 0) {
//                it.updateFontColor()
//                it.updateClickable()
            } else {
                it.validity = it.validity!! - 1
            }
        }
//        cacheFlow.value.events

//        cacheFlow.value.events.forEach {
////            decrese validity
//            if (it.initValidity !=null){
//
//            }
//        }
//        cacheFlow.value.decrementValidity()
        forceFlowUpdate()
    }

    private fun forceFlowUpdate() {
        _cacheFlow.value = _cacheFlow.value.copy(
            check = Util.check
        )
    }
}
