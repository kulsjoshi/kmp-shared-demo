package com.kuldeep.kmpshareddemo.network

import com.kuldeep.kmpshareddemo.model.Todo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class TodoApi(
    private val client: HttpClient
) {
    suspend fun fetchTodo(): Todo {
        return client
            .get("https://jsonplaceholder.typicode.com/todos/10")
            .body()
    }
}