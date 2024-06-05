package com.keidgo.todoapp.addtasks.domain

import com.keidgo.todoapp.addtasks.data.TaskRepository
import com.keidgo.todoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class AddTasksUseCase @Inject constructor(private val tasksRepository: TaskRepository) {
    suspend operator fun invoke(task: TaskModel) {
        tasksRepository.add(task)
    }
}