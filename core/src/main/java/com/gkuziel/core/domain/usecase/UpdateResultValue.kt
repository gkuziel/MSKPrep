package com.gkuziel.core.domain.usecase


import com.gkuziel.core.data.Repository
import javax.inject.Inject


class UpdateResultValue @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(eventId: String, id: String, value: Int) {

        repository.updateResult(eventId, id, value)
    }
}


