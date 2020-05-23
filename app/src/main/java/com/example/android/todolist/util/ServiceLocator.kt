package com.example.android.todolist.util

import android.content.Context
import com.example.android.todolist.db.TaskDatabase
import com.example.android.todolist.repo.DaoEntryPoint
import com.example.android.todolist.repo.DaoEntryRepo
import com.example.android.todolist.repo.TaskRepo
import com.example.android.todolist.repo.TasksRepoInterface

object ServiceLocator {

    var database: TaskDatabase? = null

    var tasksrepo: TasksRepoInterface? = null


    fun getTasksRepo(context: Context): TasksRepoInterface {
        synchronized(this) {
            return tasksrepo ?: tasksrepo ?: createTasksRepo(context)
        }
    }

    private fun createTasksRepo(context: Context): TasksRepoInterface {
        return TaskRepo(createDatabase(context))
    }

    private fun createDatabase(context: Context): DaoEntryPoint {
        val db = database ?: TaskDatabase.invoke(context)
        return DaoEntryRepo(db.dao())
    }

}