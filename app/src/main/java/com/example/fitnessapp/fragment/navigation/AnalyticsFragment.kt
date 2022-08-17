package com.example.fitnessapp.fragment.navigation

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
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
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentAnalyticsBinding
import com.example.fitnessapp.hideKeyboard
import kotlinx.android.synthetic.main.fragment_analytics.*
import kotlinx.android.synthetic.main.fragment_home.*

class AnalyticsFragment : Fragment(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var running = false
    private var totalSteps = 0f
    private var previousTotalSteps = 0f
    val ACTIVITY_RECOGNITION_REQUEST_CODE = 100

    lateinit var navController: NavController
    lateinit var binding: FragmentAnalyticsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.tvCalendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = (dayOfMonth.toString() + "-" + (month + 1) + "-" + year)
            tvData.text = date
        }

        buttonSetCurrentWeight()

        binding.progressCircular.apply {
            setProgressWithAnimation(0f)
        }

        if (isPermissionGranted()) {
            requestPermission()
        }

        loadData()
        restSteps()
        sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager


    }

    private fun buttonSetCurrentWeight() {
        binding.btnSetWeight.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Set ${edt_current_weight.text.toString()} Weight?")
                .setPositiveButton("Set") { _, _ ->
                    (activity as MainActivity).userInfo.userTargetWeight =
                        binding.edtCurrentWeight.text.toString()

                }.setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }.show()
            it.hideKeyboard()
        }
    }


    //for walk counter



    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(
                this.context as Activity,
                arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
                ACTIVITY_RECOGNITION_REQUEST_CODE
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun isPermissionGranted(): Boolean {
        return this.context?.let {
            ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.ACTIVITY_RECOGNITION
            )
        } != PackageManager.PERMISSION_GRANTED
    }

    override fun onResume() {
        super.onResume()
        running = true
        val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepSensor == null) {
            Toast.makeText(context, "No sensor detected on this device", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (running) {
            totalSteps = event!!.values[0]
            val currentSteps = totalSteps.toInt() - previousTotalSteps.toInt()
            tv_steps_token.text = ("$currentSteps")

            progress_circular.apply {
                setProgressWithAnimation(currentSteps.toFloat())
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    private fun restSteps() {
        tv_steps_token.setOnClickListener {
            Toast.makeText(context, "Long tap to rest steps", Toast.LENGTH_SHORT).show()

        }

        tv_steps_token.setOnLongClickListener {
            previousTotalSteps = totalSteps
            tv_steps_token.text = "0"
            saveData()

            true
        }

    }

    private fun saveData() {
        val sharedPreferences = context?.getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putFloat("key1", previousTotalSteps)
        editor?.apply()
    }

    private fun loadData() {
        val sharedPreferences = context?.getSharedPreferences("myPref", Context.MODE_PRIVATE)

        val savedNumber = sharedPreferences?.getFloat("key1", 0f)
//        Log.d("MainActivity", "$savedNumber")
        if (savedNumber != null) {
            previousTotalSteps = savedNumber
        }
    }
}