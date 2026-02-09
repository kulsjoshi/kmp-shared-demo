package com.kuldeep.kmpshareddemo.ui.utils

sealed class Screen {
    data object Home: Screen()
    data object TodoList: Screen()
}