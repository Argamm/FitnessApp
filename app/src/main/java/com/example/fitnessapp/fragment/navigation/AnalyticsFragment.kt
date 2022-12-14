package com.example.fitnessapp.fragment.navigation

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
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
import com.example.fitnessapp.*
import com.example.fitnessapp.databinding.FragmentAnalyticsBinding
import kotlinx.android.synthetic.main.fragment_analytics.*

class AnalyticsFragment : Fragment(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var running = false
    private var totalSteps = 0f
    private var previousTotalSteps = 0f
    private val ACTIVITY_RECOGNITION_REQUEST_CODE = 100

    lateinit var navController: NavController
    lateinit var binding: FragmentAnalyticsBinding
    lateinit var sharedPreferences: SharedPreferences
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
        sharedPreferences =
            (activity as MainActivity).getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

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
                .setTitle(
                    "${getString(R.string.set)} ${edt_current_weight.text.toString()} ${
                        getString(
                            R.string.weight
                        )
                    }"
                )
                .setPositiveButton(getString(R.string.set)) { _, _ ->
                    sharedPreferences.edit()
                        .putString(EDT_TARGET_WEIGHT, binding.edtCurrentWeight.text.toString())
                        .apply()

                }.setNegativeButton(getString(R.string.cancel_)) { dialog, _ ->
                    dialog.cancel()
                }.show()
            it.hideKeyboard()
        }
    }

    //for walk counter
    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(
                context as Activity,
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
            Toast.makeText(context, getString(R.string.restStepsCounter), Toast.LENGTH_SHORT).show()
        }

        tv_steps_token.setOnLongClickListener {
            previousTotalSteps = totalSteps
            tv_steps_token.text = getString(R.string._0)
            saveData()
            true
        }
    }

    private fun saveData() {
        val sharedPreferences = context?.getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putFloat(KYE1, previousTotalSteps)
        editor?.apply()
    }

    private fun loadData() {
        val sharedPreferences = context?.getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val savedNumber = sharedPreferences?.getFloat(KYE1, 0f)
        if (savedNumber != null) {
            previousTotalSteps = savedNumber
        }
    }
}