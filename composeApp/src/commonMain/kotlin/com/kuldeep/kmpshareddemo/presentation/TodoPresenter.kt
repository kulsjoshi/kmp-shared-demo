package com.kuldeep.kmpshareddemo.presentation

import com.kuldeep.kmpshareddemo.domain.GetTodoUseCase
import com.kuldeep.kmpshareddemo.domain.GetTodosUseCase
import com.kuldeep.kmpshareddemo.model.Todo
import com.kuldeep.kmpshareddemo.utils.ApiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * 4️⃣ Presenter (shared ViewModel-like)
 *
 * This is important
 *
 * What
 * TodoPresenter
 * Holds StateFlow<TodoUiState>
 * Exposes load() / retry()
 *
 * Why
 * Android ViewModel can’t be shared
 * Presenter has no Android / iOS imports
 *
 * Interview
 * “We use a shared Presenter exposing StateFlow instead of platform ViewModels.”
 */
class TodoPresenter(
    private val getTodo: GetTodoUseCase,
    private val getTodosUseCase: GetTodosUseCase,
    private val viewModelScope: CoroutineScope,
) {

    private val _state = MutableStateFlow<ApiState<Todo>>(ApiState.Loading)
    val state: StateFlow<ApiState<Todo>> = _state.asStateFlow()

    private val _stateTodoList = MutableStateFlow<ApiState<List<Todo>>>(ApiState.Loading)
    val stateTodoList: StateFlow<ApiState<List<Todo>>> = _stateTodoList.asStateFlow()

    fun loadTodo() {
        viewModelScope.launch {
            _state.value = ApiState.Loading
            try {
                getTodo().collect {
                    _state.value = ApiState.Success(data = it)
                }
            } catch (e: Exception) {
                _state.value = ApiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    fun loadTodoList() {
        viewModelScope.launch {
            _stateTodoList.value = ApiState.Loading
            try {
                getTodosUseCase().collect {
                    _stateTodoList.value = ApiState.Success(data = it)
                }
            } catch (e: Exception) {
                _stateTodoList.value = ApiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    fun retryTodo() = loadTodo()
    fun retryTodoList() = loadTodoList()

}