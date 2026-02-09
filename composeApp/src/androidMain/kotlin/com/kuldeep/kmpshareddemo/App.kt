package com.kuldeep.kmpshareddemo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.kuldeep.kmpshareddemo.ui.TodoListScreen
import com.kuldeep.kmpshareddemo.ui.rememberTodoPresenter
import com.kuldeep.kmpshareddemo.ui.utils.HomeScreen
import com.kuldeep.kmpshareddemo.ui.utils.Screen

@Composable
@Preview
fun App() {
    MaterialTheme {
        var screen by remember { mutableStateOf<Screen>(Screen.Home) }

        when (screen) {

            Screen.Home -> {
                HomeScreen(
                    onOpenTodoList = { screen = Screen.TodoList }
                )
            }

            Screen.TodoList -> TodoListScreen(
                todoPresenter = rememberTodoPresenter(),
                onBack = { screen = Screen.Home }
            )
        }
    }
}

