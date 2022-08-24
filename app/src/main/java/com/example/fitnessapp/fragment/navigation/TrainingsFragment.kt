package com.example.fitnessapp.fragment.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentTrainingsBinding
import com.example.fitnessapp.fragment.adapter.ViewPagerAdapter
import com.example.fitnessapp.fragment.navigation.trainings.ABSFragment
import com.example.fitnessapp.fragment.navigation.trainings.ArmsFragment
import com.example.fitnessapp.fragment.navigation.trainings.LegsFragment
import com.example.fitnessapp.fragment.navigation.trainings.ShortTrainingsFragment
import com.google.android.material.tabs.TabLayoutMediator

class TrainingsFragment : Fragment() {
    lateinit var binding: FragmentTrainingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = listOf(ArmsFragment(), LegsFragment(), ABSFragment(), ShortTrainingsFragment())
        val listNames = listOf(
            getString(R.string.arms), getString(R.string.legs), getString(R.string.abs), getString(
                R.string.shotr
            )
        )

        binding.viewPager.adapter = ViewPagerAdapter(this, list)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = listNames[position]
        }.attach()
    }

}
