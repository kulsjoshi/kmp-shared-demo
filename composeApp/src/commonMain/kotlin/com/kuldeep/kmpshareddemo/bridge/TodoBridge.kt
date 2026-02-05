package com.kuldeep.kmpshareddemo.bridge

import com.kuldeep.kmpshareddemo.data.TodoRepository
import com.kuldeep.kmpshareddemo.domain.GetTodoUseCase
import com.kuldeep.kmpshareddemo.model.Todo
import com.kuldeep.kmpshareddemo.network.HttpClientFactory
import com.kuldeep.kmpshareddemo.network.TodoApi
import com.kuldeep.kmpshareddemo.presentation.TodoPresenter
import com.kuldeep.kmpshareddemo.utils.ApiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TodoBridge {
    private val scope: CoroutineScope = MainScope()

    private val presenter = TodoPresenter(
        getTodo = GetTodoUseCase(
            TodoRepository(
                TodoApi(HttpClientFactory.create())
            )
        ),
        viewModelScope = scope
    )

    val state: StateFlow<ApiState<Todo>> = presenter.state

    fun load() = presenter.load()
    fun retry() = presenter.retry()

    /**
     * Swift-friendly observer:
     * - onLoading(true/false)
     * - onSuccess(title)
     * - onError(message)
     */
    fun observe(
        onLoading: (Boolean) -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): com.kuldeep.kmpshareddemo.bridge.Closeable {

        val currentState = presenter.state.onEach { state ->
            when(state){
                is ApiState.Loading -> onLoading(true)
                is ApiState.Success<Todo> -> {
                    onLoading(false)
                    onSuccess(state.data.title)
                }
                is ApiState.Error -> {
                    onLoading(false)
                    onError(state.message)
                }
            }
        }.launchIn(scope)


        return Closeable {
            currentState.cancel()
        }
    }

    fun clear() {
        scope.cancel()
    }
}

class Closeable(private val onClose: () -> Unit) {
    fun close() = onClose()
}