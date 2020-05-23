package com.example.android.todolist

import android.app.Application
import com.example.android.todolist.repo.TasksRepoInterface
import com.example.android.todolist.util.ServiceLocator

class TodoApplication : Application() {
    val taskRepository: TasksRepoInterface
        get() = ServiceLocator.getTasksRepo(this)
}