package com.example.android.todolist.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.todolist.data.Task
import com.example.android.todolist.ui.TaskListAdapter

@BindingAdapter("items")
fun setItems(listView: RecyclerView, items: List<Task>?) {
    items?.let {
        (listView.adapter as TaskListAdapter).differ.submitList(items)
    }
}