package com.example.android.todolist.util

import androidx.fragment.app.Fragment
import com.example.android.todolist.TodoApplication
import com.example.android.todolist.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as TodoApplication).taskRepository
    return ViewModelFactory(repository, this)
}