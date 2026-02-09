package com.kuldeep.kmpshareddemo.network

import com.kuldeep.kmpshareddemo.model.Todo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class TodoApi(
    private val client: HttpClient
) {
    suspend fun fetchTodo(id: Int): Todo {
        return client
            .get(urlString = "https://jsonplaceholder.typicode.com/todos/${id}")
            .body()
    }
    suspend fun fetchTodos(): List<Todo>{
        return client
            .get(urlString = "https://jsonplaceholder.typicode.com/todos")
            .body()
    }
}