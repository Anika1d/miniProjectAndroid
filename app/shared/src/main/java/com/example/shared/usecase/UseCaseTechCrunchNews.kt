package com.example.shared.usecase

import com.example.shared.repository.Repository
import javax.inject.Inject

class UseCaseTechCrunchNews  @Inject constructor(private val repository: Repository){
    suspend operator fun invoke() = repository.getTechCrunchNews()
}