package com.keidgo.todoapp.addtasks.domain

import com.keidgo.todoapp.addtasks.data.TaskRepository
import com.keidgo.todoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(task: TaskModel) {
        taskRepository.deleteTask(task)
    }

}