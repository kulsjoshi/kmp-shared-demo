package com.kuldeep.kmpshareddemo.bridge

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun <T> StateFlow<T>.watch(block: (T) -> Unit): DisposableHandle {
    val scope: CoroutineScope = MainScope()
    val job = scope.launch {
        collect { block(it) }
    }
    return DisposableHandle { job.cancel() }
}

class DisposableHandle(private val onDispose: () -> Unit) {
    fun dispose() = onDispose()
}
