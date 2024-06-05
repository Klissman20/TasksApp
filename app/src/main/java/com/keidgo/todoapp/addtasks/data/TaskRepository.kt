package com.keidgo.todoapp.addtasks.data

import com.keidgo.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val tasks: Flow<List<TaskModel>> =
        taskDao.getTasks().map { items -> items.map { TaskModel(it.id, it.task, it.selected) } }


    suspend fun add(taskModel: TaskModel) =
        taskDao.addTask(taskModel.toData())

    suspend fun updateTask(task: TaskModel) {
        taskDao.updateTask(task.toData())
    }

    suspend fun deleteTask(task: TaskModel) {
        taskDao.deleteTask(task.toData())
    }

}

fun TaskModel.toData() = TaskEntity(id, task, selected)