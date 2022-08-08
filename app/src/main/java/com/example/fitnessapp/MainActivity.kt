package com.example.fitnessapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val userInfo = UserInfo()
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav.visibility = View.GONE

        bottom_nav.menu.findItem(R.id.homeFragment2).isChecked = true//first checked item
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        bottom_nav.setupWithNavController(navController)
        btnNavClickHandler()

    }

    private fun btnNavClickHandler() {
        bottom_nav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.myProfileFragment -> {
                    navController.navigate(R.id.myProfileFragmentNav)
                    return@setOnItemSelectedListener true
                }
                R.id.homeFragment2 -> {
                    navController.navigate(R.id.homeFragment2)
                    return@setOnItemSelectedListener true
                }
                R.id.analyticsFragment2 -> {
                    navController.navigate(R.id.analyticsFragment2)
                    return@setOnItemSelectedListener true
                }
                R.id.customTrainingsFragment2 -> {
                    navController.navigate(R.id.customTrainingsFragment2)
                    return@setOnItemSelectedListener true
                }
                R.id.trainingsFragment2 -> {
                    navController.navigate(R.id.trainingsFragment2)
                    return@setOnItemSelectedListener true
                }
            }
            if (bottom_nav.visibility == View.GONE) {
                bottom_nav.visibility = View.VISIBLE
            }
            true
        }
    }
}