package com.keidgo.todoapp.addtasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keidgo.todoapp.addtasks.domain.AddTasksUseCase
import com.keidgo.todoapp.addtasks.domain.DeleteTaskUseCase
import com.keidgo.todoapp.addtasks.domain.GetTasksUseCase
import com.keidgo.todoapp.addtasks.domain.UpdateTaskUseCase
import com.keidgo.todoapp.addtasks.ui.TasksUiState.Success
import com.keidgo.todoapp.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTasksUseCase: AddTasksUseCase,
    private val updateTasksUseCase: UpdateTaskUseCase,
    private val deleteTasksUseCase: DeleteTaskUseCase,
    getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    val uiState: StateFlow<TasksUiState> =
        getTasksUseCase().map( ::Success )
            .catch { TasksUiState.Error(it) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TasksUiState.Loading)


    val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    //lo mejor seria hacerlo con flows
    //private val _tasks = mutableStateListOf<TaskModel>()
    //val tasks: List<TaskModel> = _tasks

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTaskAdded(task: String) {
        _showDialog.value = false

        viewModelScope.launch {
            addTasksUseCase(TaskModel(task = task))
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
//        val index = _tasks.indexOf(taskModel)
//        _tasks[index] = taskModel.copy(selected = !taskModel.selected)
        viewModelScope.launch {
            updateTasksUseCase(taskModel.copy(selected = !taskModel.selected))
        }
    }

    fun onItemRemove(taskModel: TaskModel) {
        viewModelScope.launch {
            deleteTasksUseCase(taskModel)
        }
    }
}