package com.example.android.todolist.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.todolist.data.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(task: Task): Long

    @Delete
    suspend fun deleteArticle(task: Task)

    @Query("DELETE FROM tasks WHERE id= :id")
    suspend fun deleteTaskById(id: String)

    @Query("SELECT * FROM tasks ORDER by tasks.id ASC")
    fun getAllArticles(): LiveData<List<Task>>

    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: String): Task

}