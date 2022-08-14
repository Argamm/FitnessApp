package com.example.fitnessapp.fragment.navigation.customTrainings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fitnessapp.R
import kotlinx.android.synthetic.main.fragment_edit_task.*

const val TASKS_KEY = "task"

class EditTaskFragment : Fragment() {
//    lateinit var navController: NavController
//    val tasksList: MutableList<Tasks> = mutableListOf()
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val navHostFragment =
//            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        navController = navHostFragment.navController
//
//        btnAddTask.setOnClickListener {
//            if (edtTitle.text?.isNotEmpty() == true && edtDescription.text?.isNotEmpty() == true) {
//                val task = Tasks(edtTitle.text.toString(), edtDescription.text.toString())
//                tasksList.add(task)
//            }
//
//            navController.navigate(
//                R.id.action_editTaskFragment_to_tasksFragment,
//                bundleOf(TASKS_KEY to tasksList)
//            )
//        }
    }
}