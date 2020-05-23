package com.example.android.todolist.repo

import com.example.android.todolist.data.Task
import com.example.android.todolist.db.TaskDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DaoEntryRepo(
    private val tasksDao: TaskDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DaoEntryPoint {

    override val allTasks = tasksDao.getAllArticles()

    override suspend fun insertTask(task: Task) = withContext(ioDispatcher) {
        tasksDao.insertArticle(task)
    }

    override suspend fun deleteTaskById(id: String) = withContext(ioDispatcher) {
        tasksDao.deleteTaskById(id)
    }

    override suspend fun updateTask(task: Task) = withContext(ioDispatcher) {
        tasksDao.insertArticle(task)
    }

    override suspend fun getTaskById(id: String): Task = withContext(ioDispatcher) {
        tasksDao.getTaskById(id)
    }

    override suspend fun deleteAllTasks() = withContext(ioDispatcher) {
        tasksDao.deleteAllTasks()
    }

    override suspend fun deleteDatabase() {
        TODO("Not yet implemented")
    }

}