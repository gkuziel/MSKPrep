package com.gkuziel.core.updateservice

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Timer @Inject constructor() {

    fun get(
        timerCutOff: () -> Int
    ): Flow<Int> {
        return flow {
            while (true) {
                val cutOffTime = timerCutOff()
                delay(1000)
                emit(cutOffTime)
                if (cutOffTime == 0) {
                    break
                }
            }
        }
    }


}