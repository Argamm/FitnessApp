package com.example.myfitnessapp.fragment.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.fitnessapp.R
import com.example.myfitnessapp.fragment.adapter.ViewPagerAdapter
import com.example.myfitnessapp.fragment.navigation.trainings.ArmsFragment
import com.example.myfitnessapp.fragment.navigation.trainings.LegsFragment
import com.example.myfitnessapp.fragment.navigation.trainings.ShortTrainingsFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_trainings.*

class TrainingsFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trainings,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val list = listOf(ArmsFragment(), LegsFragment(), ShortTrainingsFragment())
        val listNames = listOf("Arms", "Legs", "Short")
//        val viewPagerAdapter = ViewPagerAdapter(this, list)

        view_pager.adapter = ViewPagerAdapter(this, list)

//        val listTabs = listOf("AA", "BB", "CC", "DD", "EE")

        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = listNames[position]
        }.attach()
    }

}
