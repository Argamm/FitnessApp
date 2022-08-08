package com.example.fitnessapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.myfitnessapp.fragment.DescriptionFragment
import com.example.myfitnessapp.fragment.EditInformationFragmentDirections
import com.example.myfitnessapp.fragment.navigation.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity:AppCompatActivity() {
    val userInfo = UserInfo()
//    private val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
//    private val navController = navHostFragment.navController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav.visibility = View.GONE

        val descriptionFragment = DescriptionFragment()
        supportFragmentManager.beginTransaction().add(R.id.container, descriptionFragment)
            .addToBackStack("description").commit()

        bottom_nav.menu.findItem(R.id.homeFragment2).isChecked = true//first checked item

        //navigation menu click handler
        btnNavClickHandler()





//        val fragmentList = listOf<Fragment>(AnalyticsFragment(), CustomTrainingsFragment(), HomeFragment(), MyProfileFragment(), TrainingsFragment())
//        setAdapter(fragmentList, view_pager)
//        val listTabs = listOf("AA", "BB", "CC", "DD", "EE")

//verevi tab era sarqum yst listi u fragmentneri
//        TabLayoutMediator(tab_layout, view_pager) {tab, position->
//            tab.text = listTabs[position]
//        }.attach()
    }
    private fun btnNavClickHandler() {
        bottom_nav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.myProfileFragment -> {
                    val myProfileFragment = MyProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, myProfileFragment).addToBackStack("myProfile")
                        .commit()
//                    navController.navigate(R.id.action_secondFragment_to_thirdFragment2)


                }
                R.id.homeFragment2 -> {
                    val homeFragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, homeFragment).addToBackStack("home")
                        .commit()
                }
                R.id.analyticsFragment2 -> {
                    val analyticsFragment = AnalyticsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, analyticsFragment).addToBackStack("analytics")
                        .commit()
                }
                R.id.customTrainingsFragment2 -> {
                    val customTrainingsFragment = CustomTrainingsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, customTrainingsFragment).addToBackStack("customTrainings")
                        .commit()
                }
                R.id.trainingsFragment2 -> {
                    val trainingsFragment = TrainingsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, trainingsFragment).addToBackStack("trainings")
                        .commit()
                }
            }
            if (bottom_nav.visibility == View.GONE) {
                bottom_nav.visibility = View.VISIBLE
            }
            true
        }
    }

//    private fun setAdapter(list: List<Fragment>, viewPager: ViewPager2) {
//        val viewPagerAdapter = ViewPagerAdapter(this, list)
//        viewPager.adapter = viewPagerAdapter
//    }
}