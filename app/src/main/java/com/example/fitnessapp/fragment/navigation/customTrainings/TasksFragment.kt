package com.example.fitnessapp.fragment.navigation.customTrainings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentTasksBinding
import com.example.fitnessapp.fragment.adapter.CustomRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_tasks.*


class TasksFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var binding: FragmentTasksBinding
    val tasksList: MutableList<Tasks> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recAdapter = CustomRecyclerViewAdapter()
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        binding.recyclerView.adapter = recAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.tasksGroup.visibility = View.GONE

        binding.btnAddTask.setOnClickListener {
            if (binding.edtTitle.text?.isNotEmpty() == true
                && binding.edtDescription.text?.isNotEmpty() == true
            ) {
                val task =
                    Tasks(binding.edtTitle.text.toString(), binding.edtDescription.text.toString())

                tasksList.add(task)
                recAdapter.updateData(tasksList)
                it.hideKeyboard()
            }

            binding.tasksGroup.visibility = View.VISIBLE
            binding.newTaskGroup.visibility = View.GONE
        }

        btnTaskCreateStart.setOnClickListener {
            binding.tasksGroup.visibility = View.GONE
            binding.newTaskGroup.visibility = View.VISIBLE
        }

        btnSeeTasks.setOnClickListener {
            binding.tasksGroup.visibility = View.VISIBLE
            binding.newTaskGroup.visibility = View.GONE
        }
    }

    fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
}