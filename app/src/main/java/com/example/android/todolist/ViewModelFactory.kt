package com.example.android.todolist

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.android.todolist.repo.TasksRepoInterface
import com.example.android.todolist.ui.TasksViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val taskRepo: TasksRepoInterface,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = with(modelClass) {
        when {
            isAssignableFrom(TasksViewModel::class.java) ->
                TasksViewModel(taskRepo)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}