package com.example.fitnessapp

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fitnessapp.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference =
            (this).getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val language = sharedPreference.getString(LANG, "")
        setLocale(this, language)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNav.menu.findItem(R.id.homeFragment2).isChecked = true//first checked item
        binding.bottomNav.setupWithNavController(navController)

        btnNavClickHandler()

        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun btnNavClickHandler() {
        binding.bottomNav.setOnItemSelectedListener { item ->
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
            if (binding.bottomNav.visibility == View.GONE) {
                binding.bottomNav.visibility = View.VISIBLE
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
                AlertDialog.Builder(this).setTitle(getString(R.string.exit_)).setPositiveButton(
                    getString(
                        R.string.ok
                    )
                ) { _, _ ->
                    this.finish()
                }.setNegativeButton(getString(R.string.cancel_)) { di, _ ->
                    di.cancel()
                }.show()
            }
            R.id.toolbarSettings -> {
                navController.navigate(R.id.myProfileFragmentNav)
            }
            R.id.toolbarSearch -> Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        }
        return true
    }
}

