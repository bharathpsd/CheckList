package com.example.android.todolist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.todolist.R
import com.example.android.todolist.databinding.FragmentsTasksBinding
import com.example.android.todolist.util.EventObserver
import com.example.android.todolist.util.getViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragments_tasks.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */


class TasksFragment : Fragment() {

    private val viewModel by viewModels<TasksViewModel> { getViewModelFactory() }
    private lateinit var viewBinding: FragmentsTasksBinding

    private lateinit var listAdapter: TaskListAdapter

    companion object {
        val TAG = TasksFragment::class.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentsTasksBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewBinding.viewmodel = viewModel
        viewBinding.lifecycleOwner = this.viewLifecycleOwner
        setUpViews()
        setUpAdapter()
        observeLiveData()
    }

    private fun setUpAdapter() {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewBinding.viewmodel?.let {
            listAdapter = TaskListAdapter(viewModel)
            viewBinding.recyclerView.layoutManager = layoutManager
            viewBinding.recyclerView.adapter = listAdapter
        }
    }

    private fun setUpViews() {
        fab.setOnClickListener {
            Snackbar.make(
                it,
                R.string.app_name, Snackbar.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }

    private fun observeLiveData() {
        viewModel.openTask.observe(viewLifecycleOwner, EventObserver {
            openTask(it)
        })
    }

    private fun openTask(taskId: Int) {
        val taskID = taskId.toString()
        val action = TasksFragmentDirections.actionFirstFragmentToSecondFragment(taskID)
        findNavController().navigate(action)
    }
}
