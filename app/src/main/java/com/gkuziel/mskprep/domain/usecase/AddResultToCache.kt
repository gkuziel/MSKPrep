package com.gkuziel.mskprep.domain.usecase


import com.gkuziel.mskprep.data.Repository
import javax.inject.Inject


class AddResultToCache @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(eventId: String, id: String, desc: String, value: Int) {
        repository.addResult(eventId, id, desc, value)
    }
}


