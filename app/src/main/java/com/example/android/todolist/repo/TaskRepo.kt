package com.example.android.todolist.repo

import com.example.android.todolist.data.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class TaskRepo(
    private val localDataStorage: DaoEntryPoint,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TasksRepoInterface {

    override val allTasks = localDataStorage.allTasks

    override suspend fun insertTask(task: Task) = withContext(ioDispatcher) {
        localDataStorage.insertTask(task)
    }

    override suspend fun deleteTaskById(id: String) = withContext(ioDispatcher) {
        localDataStorage.deleteTaskById(id)
    }

    override suspend fun updateTask(task: Task) = withContext(ioDispatcher) {
        localDataStorage.updateTask(task)
    }

    override suspend fun getTaskById(id: String): Task = withContext(ioDispatcher) {
        localDataStorage.getTaskById(id)
    }

    override suspend fun deleteAllTasks() = withContext(ioDispatcher) {
        localDataStorage.deleteAllTasks()
    }

    override suspend fun deleteDatabase() = withContext(ioDispatcher) {
        localDataStorage.deleteDatabase()
    }

}