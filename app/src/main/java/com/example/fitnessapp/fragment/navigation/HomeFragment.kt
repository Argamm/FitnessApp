package com.example.myfitnessapp.fragment.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import com.example.fitnessapp.fragment.navigation.home.HomeMainFragment
import com.example.fitnessapp.fragment.navigation.home.TasksFragment
import com.example.myfitnessapp.fragment.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_trainings.*

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fragmentsList = listOf<Fragment>(HomeMainFragment(), TasksFragment())
        view_pager_home.adapter = ViewPagerAdapter(this, fragmentsList)
        val listNames = listOf<String>("Home", "Tasks")

        TabLayoutMediator(tab_layout_home, view_pager_home) { tab, position ->
            tab.text = listNames[position]
        }.attach()
    }
}