package com.example.android.todolist.ui

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.android.todolist.data.Task
import com.example.android.todolist.repo.TasksRepoInterface
import com.example.android.todolist.util.Event
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class TasksViewModel constructor(
    private val tasksRepo: TasksRepoInterface
) : ViewModel() {

    private val _loading = MutableLiveData(false)

    private val _dataLoading = MutableLiveData(false)
    val dataLoading: LiveData<Boolean> = _loading

    private val _empty = MutableLiveData(true)
    val empty: LiveData<Boolean> = _empty

    private val _openTask = MutableLiveData<Event<Int>>()
    val openTask: LiveData<Event<Int>> = _openTask

    val title = ObservableField("")
    val desc = ObservableField("")


    private val refresh: LiveData<List<Task>> = _loading.switchMap { loading ->
        _dataLoading.value = true
        if (loading) {
            viewModelScope.launch {
                tasksRepo.allTasks
                _dataLoading.value = false
            }
        }
        tasksRepo.allTasks.distinctUntilChanged().switchMap { getListOfTasks() }
    }

    val tasks: LiveData<List<Task>> = refresh

    init {
        _loading.value = true
    }

    private fun getListOfTasks(): LiveData<List<Task>> {
        val result = MutableLiveData<List<Task>>()
        viewModelScope.launch {
            result.value = tasksRepo.allTasks.value
            result.value?.let {
                Log.e("TaskViewModel", "The list size is : ${it.size}")
                Log.e("TaskViewModel", "The list is $it")
                if (it.isEmpty()) {
                    Log.e("TaskViewModel", "The list is empty")
                    _empty.value = false
                }
            }
        }
        return result
    }

    fun openTask(taskId: Int) {
        _openTask.value = Event(taskId)
    }

    fun insertTask(titleView: TextInputLayout, descView: TextInputLayout) {
        titleView.isErrorEnabled = false
        descView.isErrorEnabled = false
        if (title.get()!!.isEmpty()) {
            titleView.isErrorEnabled = true
            titleView.error = "Title cannot be empty."
        }

        if (desc.get()!!.isEmpty()) {
            descView.isErrorEnabled = true
            descView.error = "Description cannot be empty."
        }

        if (title.get()!!.isNotEmpty() && desc.get()!!.isNotEmpty()) {
            viewModelScope.launch {
                val task = Task(title = title.get().toString(), description = desc.get().toString())
                tasksRepo.insertTask(task)
            }
        }
    }
}