package com.example.fitnessapp.fragment.navigation.customTrainings

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.PREFERENCE_NAME
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentTasksBinding
import com.example.fitnessapp.fragment.adapter.CustomRecyclerViewAdapter
import com.example.fitnessapp.hideKeyboard
import kotlinx.android.synthetic.main.fragment_tasks.*


class TasksFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var binding: FragmentTasksBinding
    lateinit var sharedPreferences: SharedPreferences
    var tasksList: MutableList<Tasks> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        sharedPreferences =
            (activity as MainActivity).getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

        var newTasks: MutableList<Tasks> = mutableListOf()
        var bool: Boolean = true
        val recAdapter = CustomRecyclerViewAdapter() { taskList, position ->
            AlertDialog.Builder(context).setTitle(getString(R.string.deleteItem))
                .setPositiveButton(getString(R.string.yes)) { di, _ ->
                    taskList.removeAt(position)
                    tasksList.removeAt(position)
                    recyclerView.adapter?.notifyDataSetChanged()
                    recyclerView.adapter?.notifyItemRemoved(position);


                    recyclerView.clearOnChildAttachStateChangeListeners()
                }.setNegativeButton(getString(R.string.no)) { di, _ ->
                di.dismiss()
            }.show()
        }

//        recAdapter.task = newTasks
        recyclerView.adapter = recAdapter
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        with(binding) {
            recyclerView.adapter = recAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)

            tasksGroup.visibility = View.GONE

            btnAddTask.setOnClickListener {
                if (edtTitle.text?.isNotEmpty() == true
                    && edtDescription.text?.isNotEmpty() == true
                ) {
                    val task =
                        Tasks(edtTitle.text.toString(), edtDescription.text.toString())

                    tasksList.add(task)


                    recAdapter.updateData(tasksList)

                    it.hideKeyboard()
                }
                tasksGroup.visibility = View.VISIBLE
                newTaskGroup.visibility = View.GONE
            }
        }

        binding.btnTaskCreateStart.setOnClickListener {
            binding.tasksGroup.visibility = View.GONE
            binding.newTaskGroup.visibility = View.VISIBLE
        }

        binding.btnSeeTasks.setOnClickListener {
            binding.tasksGroup.visibility = View.VISIBLE
            binding.newTaskGroup.visibility = View.GONE
        }
    }
}