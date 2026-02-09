package com.kuldeep.kmpshareddemo.ui.utils

import com.kuldeep.kmpshareddemo.model.Todo

sealed class Screen {
    data object Home: Screen()
    data object TodoList: Screen()
    data class TodoDetails(val todo: Todo): Screen()
}