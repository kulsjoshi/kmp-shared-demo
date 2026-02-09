package com.kuldeep.kmpshareddemo.data

import com.kuldeep.kmpshareddemo.model.Todo
import com.kuldeep.kmpshareddemo.network.TodoApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * 2️⃣ Repository (shared)
 *
 * What
 * TodoRepository
 * Hides where data comes from (API now, DB later)
 *
 * Why
 * UI doesn’t care about networking
 * Easy to swap data source
 *
 * Interview
 * “Repository abstracts data sources and exposes flows.”
 */
class TodoRepository(private val api: TodoApi) {
    fun getTodo(id: Int): Flow<Todo> = flow {
        emit(value = api.fetchTodo(id))
    }

    fun getTodos():Flow<List<Todo>> = flow{
        emit(value = api.fetchTodos())
    }
}