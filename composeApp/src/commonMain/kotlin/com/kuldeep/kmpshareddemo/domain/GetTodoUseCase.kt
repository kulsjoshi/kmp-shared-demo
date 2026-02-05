package com.kuldeep.kmpshareddemo.domain

import com.kuldeep.kmpshareddemo.data.TodoRepository

/**
 * 3️⃣ UseCase (shared)
 *
 * What
 * GetTodoUseCase
 * Thin wrapper around repository
 *
 * Why
 * Business rules live here
 * Makes testing and reuse easier
 *
 * Interview
 * “UseCases keep business logic independent from UI.”
 */
class GetTodoUseCase(
    private val repository: TodoRepository
) {
    operator fun invoke() = repository.getTodo()
}