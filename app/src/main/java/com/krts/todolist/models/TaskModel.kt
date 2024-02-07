package com.krts.todolist.models

data class TaskModel(
    var id: Int,
    var name: String,
    var isCompleted: Boolean = false
)