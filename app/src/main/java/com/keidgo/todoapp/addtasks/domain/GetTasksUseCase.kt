package com.keidgo.todoapp.addtasks.domain

import com.keidgo.todoapp.addtasks.data.TaskRepository
import com.keidgo.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val tasksRepository: TaskRepository
) {
    operator fun invoke(): Flow<List<TaskModel>> = tasksRepository.tasks
}