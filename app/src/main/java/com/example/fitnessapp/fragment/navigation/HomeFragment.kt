package com.example.fitnessapp.fragment.navigation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentHomeBinding
import com.example.fitnessapp.fragment.navigation.home.MyDialogFragment
import kotlinx.android.synthetic.main.fragment_home.*

const val INFO = "info"
const val IMG = "img"
const val NAME = "name"

class HomeFragment : Fragment() {


    lateinit var navController: NavController
    val bundle = Bundle()
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        lottieClickHandler()


    }

    private fun lottieClickHandler() {
        with(binding) {
            lottieHealthyFood.setOnClickListener {
                val dialog = MyDialogFragment(R.raw.walking_avocado,getString(R.string.food_info) )
                activity?.supportFragmentManager?.let { it1 ->
                    dialog.show(
                        it1,
                        "customDialog1"
                    )
                }
            }
            lottieSleep.setOnClickListener {
                val dialog = MyDialogFragment(R.raw.sleep, getString(R.string.sleep_info))
                activity?.supportFragmentManager?.let { it1 ->
                    dialog.show(
                        it1,
                        "customDialog1"
                    )
                }
            }

            lottieWater.setOnClickListener {
                val dialog = MyDialogFragment(R.raw.water, getString(R.string.water_info))
                activity?.supportFragmentManager?.let { it1 ->
                    dialog.show(
                        it1,
                        "customDialog1"
                    )
                }
            }
            lottieGif.setOnClickListener {
                if (lottieGif.isAnimating)
                    lottieGif.pauseAnimation()
                else
                    lottieGif.playAnimation()
            }
        }
    }
}
