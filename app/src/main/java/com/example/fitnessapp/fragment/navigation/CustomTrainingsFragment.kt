package com.example.fitnessapp.fragment.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentCustomTrainingsBinding
import com.example.fitnessapp.fragment.adapter.ViewPagerAdapter
import com.example.fitnessapp.fragment.navigation.customTrainings.CustomTrainFragment
import com.example.fitnessapp.fragment.navigation.customTrainings.TasksFragment
import com.google.android.material.tabs.TabLayoutMediator

class CustomTrainingsFragment : Fragment() {
    lateinit var binding: FragmentCustomTrainingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomTrainingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = listOf(TasksFragment(), CustomTrainFragment())
        val listNames = listOf(getString(R.string.tasks), getString(R.string.myTrainings))

        binding.viewPagerCustomTrain.adapter = ViewPagerAdapter(this, list)
        TabLayoutMediator(
            binding.tabLayoutCustomTrain,
            binding.viewPagerCustomTrain
        ) { tab, position ->
            tab.text = listNames[position]
        }.attach()
    }
}