package com.example.fitnessapp.fragment.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.fragment.adapter.CustomRecyclerViewAdapter
import com.example.fitnessapp.fragment.navigation.customTrainings.CustomTrainFragment
import com.example.fitnessapp.fragment.navigation.customTrainings.TASKS_KEY
import com.example.fitnessapp.fragment.navigation.customTrainings.Tasks
import com.example.fitnessapp.fragment.navigation.customTrainings.TasksFragment
import com.example.fitnessapp.fragment.navigation.trainings.ABSFragment
import com.example.myfitnessapp.fragment.adapter.ViewPagerAdapter
import com.example.myfitnessapp.fragment.navigation.trainings.ArmsFragment
import com.example.myfitnessapp.fragment.navigation.trainings.LegsFragment
import com.example.myfitnessapp.fragment.navigation.trainings.ShortTrainingsFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_custom_trainings.*
import kotlinx.android.synthetic.main.fragment_trainings.*

class CustomTrainingsFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_custom_trainings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val list = listOf(CustomTrainFragment(), TasksFragment())
        val listNames = listOf("My Trainings", "Tasks")

        view_pager_custom_train.adapter = ViewPagerAdapter(this, list)

        TabLayoutMediator(tab_layout_custom_train, view_pager_custom_train) { tab, position ->
            tab.text = listNames[position]
        }.attach()



    }
}