package com.example.android.todolist.repo

import androidx.lifecycle.LiveData
import com.example.android.todolist.data.Task

interface TasksRepoInterface {

    val allTasks: LiveData<List<Task>>

    suspend fun insertTask(task: Task): Long

    suspend fun deleteTaskById(id: String)

    suspend fun updateTask(task: Task): Long

    suspend fun getTaskById(id: String): Task

    suspend fun deleteAllTasks()

    suspend fun deleteDatabase()

}