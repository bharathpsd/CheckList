package com.example.android.todolist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.todolist.R
import com.example.android.todolist.databinding.FragmentAddEditTaskBinding
import com.example.android.todolist.util.getViewModelFactory
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddEditTask : Fragment() {

    private val viewModel by viewModels<TasksViewModel> { getViewModelFactory() }
    private lateinit var viewBinding: FragmentAddEditTaskBinding

    private val args: AddEditTaskArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentAddEditTaskBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.viewmodel = viewModel
        viewBinding.lifecycleOwner = viewLifecycleOwner
        setArgs()
        setClickListeners()
    }

    private fun setArgs() {
        arguments?.let {
            Snackbar.make(requireView(), "${args.taskId} received", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setClickListeners() {

    }

    fun navigateToTasks(view: View) {
        view.findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }
}
