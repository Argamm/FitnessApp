package com.example.fitnessapp.fragment.navigation.customTrainings

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val recAdapter = CustomRecyclerViewAdapter()

        recyclerView.adapter = recAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        val userList = arguments?.getParcelableArrayList<Tasks>(TASKS_KEY) ?: mutableListOf()
        recAdapter.updateData(userList)

        tasks_group.visibility = View.GONE

        binding.btnAddTask.setOnClickListener {
            if (edtTitle.text?.isNotEmpty() == true && edtDescription.text?.isNotEmpty() == true) {
                val task = Tasks(edtTitle.text.toString(), edtDescription.text.toString())
                tasksList.add(task)

                recAdapter.updateData(tasksList)

                it.hideKeyboard()
            }

            tasks_group.visibility = View.VISIBLE
            new_task_group.visibility = View.GONE
        }

        btnTaskCreateStart.setOnClickListener {
            tasks_group.visibility = View.GONE
            new_task_group.visibility = View.VISIBLE
        }

        btnSeeTasks.setOnClickListener {
            tasks_group.visibility = View.VISIBLE
            new_task_group.visibility = View.GONE
        }
    }

    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
}