package com.gkuziel.core.domain.usecase


import com.gkuziel.core.data.Repository
import javax.inject.Inject


class LoadUsers @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute() {
        return repository.loadUsers()
    }
}


