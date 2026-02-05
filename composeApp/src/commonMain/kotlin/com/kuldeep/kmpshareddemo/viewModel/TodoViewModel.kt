package com.kuldeep.kmpshareddemo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuldeep.kmpshareddemo.data.TodoRepository
import com.kuldeep.kmpshareddemo.domain.GetTodoUseCase
import com.kuldeep.kmpshareddemo.network.HttpClientFactory
import com.kuldeep.kmpshareddemo.network.TodoApi
import com.kuldeep.kmpshareddemo.presentation.TodoPresenter

class TodoViewModel : ViewModel() {

    private val todoPresenter: TodoPresenter = TodoPresenter(
        GetTodoUseCase(
            repository = TodoRepository(
                api = TodoApi(
                    client = HttpClientFactory.create()
                )
            )
        ), viewModelScope
    )

    val state = todoPresenter.state

    init {
        todoPresenter.load()
    }

    fun retry() = todoPresenter.retry()

}