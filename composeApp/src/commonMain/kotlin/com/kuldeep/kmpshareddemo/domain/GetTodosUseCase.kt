package com.kuldeep.kmpshareddemo.domain

import com.kuldeep.kmpshareddemo.data.TodoRepository

class GetTodosUseCase(
    private val repository: TodoRepository
) {
    operator fun invoke() = repository.getTodos()
}