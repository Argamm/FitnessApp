package com.example.fitnessapp.fragment.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import com.example.fitnessapp.fragment.navigation.home.MyDialogFragment
import kotlinx.android.synthetic.main.fragment_home.*

const val INFO = "info"
const val IMG = "img"
const val NAME = "name"

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val customDialog = MyDialogFragment()
        val bundle = Bundle()

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
    }
}
