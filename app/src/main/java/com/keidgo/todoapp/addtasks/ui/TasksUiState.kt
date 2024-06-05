package com.keidgo.todoapp.addtasks.ui

import com.keidgo.todoapp.addtasks.ui.model.TaskModel

sealed interface TasksUiState {
    data class Success(val tasks: List<TaskModel>) : TasksUiState
    object Loading : TasksUiState
    data class Error(val throwable: Throwable) : TasksUiState

}