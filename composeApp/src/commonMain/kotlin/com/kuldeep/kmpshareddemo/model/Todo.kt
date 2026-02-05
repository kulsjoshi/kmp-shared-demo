package com.kuldeep.kmpshareddemo.model

import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val id: Int,
    val title: String,
    val completed: Boolean
)