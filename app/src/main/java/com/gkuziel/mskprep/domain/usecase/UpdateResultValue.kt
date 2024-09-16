package com.gkuziel.mskprep.domain.usecase


import com.gkuziel.mskprep.data.Repository
import javax.inject.Inject


class UpdateResultValue @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(eventId: String, id: String, value: Int) {

        repository.updateResult(eventId, id, value)
    }
}


