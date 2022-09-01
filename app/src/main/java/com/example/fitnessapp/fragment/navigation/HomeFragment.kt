package com.example.fitnessapp.fragment.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentHomeBinding
import com.example.fitnessapp.fragment.navigation.home.MyDialogFragment
import com.example.fitnessapp.userAniumationDatas.UserAnimationData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private val viewModel by viewModels<HomeViewModel>()
    lateinit var courseList: ArrayList<UserAnimationData>

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
        courseList = ArrayList()

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        viewModel.getAnimationsData()
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.list.collect() {
                viewModel.list.value?.let { it1 -> lottieClickHandler(it1) }
            }
        }
    }

    private fun lottieClickHandler(courseList: ArrayList<UserAnimationData>) {
        with(binding) {
            lottieHealthyFood.setOnClickListener {
                val dialog = MyDialogFragment(courseList[0].anim1, getString(R.string.food_info))
                activity?.supportFragmentManager?.let { it1 ->
                    dialog.show(
                        it1,
                        "customDialog1"
                    )
                }
            }
            lottieSleep.setOnClickListener {
                val dialog = MyDialogFragment(courseList[0].anim2, getString(R.string.sleep_info))
                activity?.supportFragmentManager?.let { it1 ->
                    dialog.show(
                        it1,
                        "customDialog1"
                    )
                }
            }

            lottieWater.setOnClickListener {
                val dialog = MyDialogFragment(courseList[0].anim3, getString(R.string.water_info))
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
