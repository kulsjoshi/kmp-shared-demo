package com.kuldeep.kmpshareddemo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.kuldeep.kmpshareddemo.data.TodoRepository
import com.kuldeep.kmpshareddemo.domain.GetTodoUseCase
import com.kuldeep.kmpshareddemo.domain.GetTodosUseCase
import com.kuldeep.kmpshareddemo.network.HttpClientFactory
import com.kuldeep.kmpshareddemo.network.TodoApi
import com.kuldeep.kmpshareddemo.presentation.TodoPresenter
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Composable
fun rememberTodoPresenter(): TodoPresenter {
    return remember {
        val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

        val client = HttpClientFactory.create()
        val api = TodoApi(client)
        val repository = TodoRepository(api)

        TodoPresenter(
            getTodo = GetTodoUseCase(repository = repository),
            getTodosUseCase = GetTodosUseCase(repository = repository),
            viewModelScope = viewModelScope
        )
    }
}