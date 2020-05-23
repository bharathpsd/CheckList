package com.example.android.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.todolist.R
import com.example.android.todolist.data.Task
import com.example.android.todolist.databinding.TasksLayoutBinding

class TaskListAdapter(
    private val tasksViewModel: TasksViewModel
) : RecyclerView.Adapter<TaskListAdapter.TaskAdapterViewHolder>() {

    class TaskAdapterViewHolder(val binding: TasksLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: TasksViewModel, task: Task) {
            binding.viewmodel = viewModel
            binding.task = task
            binding.executePendingBindings()
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TaskAdapterViewHolder(
            DataBindingUtil.inflate(layoutInflater, R.layout.tasks_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TaskAdapterViewHolder, position: Int) {
        val task = differ.currentList[position]
        holder.bind(tasksViewModel, task)
    }

    private var onItemClickListener: ((Task) -> Unit)? = null

    fun setOnItemClickListener(listener: (Task) -> Unit) {
        onItemClickListener = listener
    }
}