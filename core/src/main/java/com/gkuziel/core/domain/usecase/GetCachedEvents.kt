package com.gkuziel.core.domain.usecase


import com.gkuziel.core.data.Repository
import com.gkuziel.core.presentation.main.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetCachedEvents @Inject constructor(
    private val repository: Repository
) {
    fun execute(): Flow<UIState> {
        return repository.getCachedEvents().map {
//            mapper.execute(it.events)
            it
        }
    }
}


