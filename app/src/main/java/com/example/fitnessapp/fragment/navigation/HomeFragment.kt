package com.example.fitnessapp.fragment.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentHomeBinding
import com.example.fitnessapp.fragment.navigation.home.MyDialogFragment

const val INFO = "info"
const val IMG = "img"
const val NAME = "name"

class HomeFragment : Fragment() {
    val bundle = Bundle()
    val customDialog = MyDialogFragment()
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
        lottieClickHandler()

    }

    fun lottieClickHandler() {
        with(binding) {
            lottieHealthyFood.setOnClickListener {
                if (context != null) {
//                bundle.putString(NAME, "food")
                    bundle.putInt(IMG, R.raw.walking_avocado)
                    bundle.putString(INFO, getString(R.string.food_info))
                    customDialog.arguments = bundle
                    activity?.supportFragmentManager?.let { it1 ->
                        customDialog.show(
                            it1,
                            "customDialog1"
                        )
                    }
                }
            }
            lottieSleep.setOnClickListener {
                if (context != null) {
//                bundle.putString(NAME, "sleep")
                    bundle.putInt(IMG, R.raw.sleep)
                    bundle.putString(INFO, getString(R.string.sleep_info))
                    customDialog.arguments = bundle
                    activity?.supportFragmentManager?.let { it1 ->
                        customDialog.show(
                            it1,
                            "customDialog2"
                        )
                    }
                }
            }

            lottieWater.setOnClickListener {
                if (context != null) {
//                bundle.putString(NAME, "water")
                    bundle.putInt(IMG, R.raw.water)
                    bundle.putString(INFO, getString(R.string.water_info))
                    customDialog.arguments = bundle
                    activity?.supportFragmentManager?.let { it1 ->
                        customDialog.show(
                            it1,
                            "customDialog3"
                        )
                    }
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
