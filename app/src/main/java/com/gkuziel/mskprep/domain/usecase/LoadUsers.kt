package com.gkuziel.mskprep.domain.usecase


import com.gkuziel.mskprep.data.Repository
import com.gkuziel.mskprep.presentation.main.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class LoadUsers @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute() {
        return repository.loadUsers()
    }
}


