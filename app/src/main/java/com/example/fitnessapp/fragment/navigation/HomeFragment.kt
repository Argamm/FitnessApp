package com.example.fitnessapp.fragment.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val fragmentsList = listOf<Fragment>(HomeMainFragment(), TasksFragment())
//
//        view_pager_home.adapter = ViewPagerAdapter(this, fragmentsList)
//
//        val listNames = listOf("Home", "Tasks")
//
//        TabLayoutMediator(tab_layout_home, view_pager_home) { tab, position ->
//            tab.text = listNames[position]
//        }.attach()
        lottieHealthyFood.setOnClickListener {
            Toast.makeText(context, "Avo Cadooo", Toast.LENGTH_SHORT).show()
        }
    }
}