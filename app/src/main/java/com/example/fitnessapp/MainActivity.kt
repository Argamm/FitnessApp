package com.example.fitnessapp

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fitnessapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var bottom_nav: BottomNavigationView
    lateinit var myToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreference =
            (this).getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

        val language = sharedPreference.getString(LANG, "")
        setLocale(this, language)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        bottom_nav = binding.bottomNav
        myToolbar = binding.myToolbar

        bottom_nav.menu.findItem(R.id.homeFragment2).isChecked = true//first checked item

        bottom_nav.setupWithNavController(navController)
        btnNavClickHandler()

        val toolbar = findViewById<Toolbar>(R.id.myToolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)//cuyc a talis het gnalu slaqy

    }

    private fun btnNavClickHandler() {
        bottom_nav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.myProfileFragmentNav -> {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.toolbarExit -> {
                AlertDialog.Builder(this).setTitle(getString(R.string.exit_)).setPositiveButton(getString(
                                    R.string.ok)) { _, _ ->
                    this.finish()
                }.setNegativeButton(getString(R.string.cancel_)) { di, _ ->
                    di.cancel()
                }.show()
            }
            R.id.toolbarSettings -> {
//                Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.myProfileFragmentNav)

            }
            R.id.toolbarSearch -> Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        }
        return true
    }

}

