package com.example.fitnessapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myfitnessapp.fragment.DescriptionFragment
import com.example.myfitnessapp.fragment.navigation.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottom_nav.visibility = View.GONE

        val descriptionFragment = DescriptionFragment()
        supportFragmentManager.beginTransaction().add(R.id.container, descriptionFragment)
            .addToBackStack("description").commit()

        bottom_nav.menu.findItem(R.id.home).isChecked = true//first checked item

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
                R.id.profile -> {
                    val myProfileFragment = MyProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, myProfileFragment).addToBackStack("myProfile")
                        .commit()
                }
                R.id.home -> {
                    val homeFragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, homeFragment).addToBackStack("home")
                        .commit()
                }
                R.id.analytics -> {
                    val analyticsFragment = AnalyticsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, analyticsFragment).addToBackStack("analytics")
                        .commit()
                }
                R.id.custom -> {
                    val customTrainingsFragment = CustomTrainingsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, customTrainingsFragment).addToBackStack("customTrainings")
                        .commit()
                }
                R.id.trainings -> {
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