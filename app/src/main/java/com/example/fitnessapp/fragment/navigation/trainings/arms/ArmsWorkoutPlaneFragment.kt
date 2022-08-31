package com.example.fitnessapp.fragment.navigation.trainings.arms

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentArmsWorkoutPlaneBinding

class ArmsWorkoutPlaneFragment : Fragment() {
    lateinit var binding: FragmentArmsWorkoutPlaneBinding
    private var timer: CountDownTimer? = null
    private var timerTwo: CountDownTimer? = null
    var array: IntArray = intArrayOf()
    var index = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArmsWorkoutPlaneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navFragmentArgs: ArmsWorkoutPlaneFragmentArgs by navArgs()
        array = navFragmentArgs.list!!

        with(binding) {
            lottieRandomTask.setAnimation(array[0])

            btnStartTimer.setOnClickListener {
                tvTimeAndRest.text = getString(R.string.exercising)

                startCountDownTimer(55000)

            }
        }
    }

    private fun startCountDownTimer(timeMillis: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(timeMillis, 1000) {
            override fun onTick(timeM: Long) {
                binding.tvTimer.text = timeM.toString().substring(0, 2)
            }

            override fun onFinish() {
                binding.tvTimeAndRest.text = getString(R.string.restTime)
                startCountDownTimerTwo(20000)
            }
        }.start()
    }

    private fun startCountDownTimerTwo(timeMillis: Long) {
        with(binding) {

            tvTimeAndRest.text = getString(R.string.restTime)
            lottieRandomTask.setAnimation(R.raw.rest_man)
            lottieRandomTask.playAnimation()
            timerTwo?.cancel()
            timerTwo = object : CountDownTimer(timeMillis, 1000) {
                override fun onTick(timeM: Long) {
                    tvTimer.text = timeM.toString().substring(0, 2)
                }

                override fun onFinish() {
                    repeat(array.size) {
                        if (array.size == index) {
                            tvFinishedText.visibility = View.VISIBLE
                            groupExercising.visibility = View.GONE
                        } else {
                            tvTimeAndRest.text = getString(R.string.exercising)
                            lottieRandomTask.setAnimation(array[index])
                            lottieRandomTask.playAnimation()
                            startCountDownTimer(10000)
                        }
                    }
                }
            }.start()
            index++
        }
    }
}