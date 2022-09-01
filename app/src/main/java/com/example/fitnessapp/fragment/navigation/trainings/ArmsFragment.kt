package com.example.fitnessapp.fragment.navigation.trainings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentArmsBinding
import com.example.fitnessapp.fragment.adapter.RecyclerViewAdapterTrainings
import com.example.fitnessapp.fragment.navigation.TrainingsFragmentDirections
import com.example.fitnessapp.userAniumationDatas.UserTrainingsData
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ArmsFragment : Fragment() {
    lateinit var binding: FragmentArmsBinding
    lateinit var navController: NavController
    lateinit var animList: ArrayList<UserTrainingsData>
    private val viewModel by viewModels<ArmsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animList = ArrayList()

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val animationsList1 = listOf(R.raw.ex1, R.raw.ex2, R.raw.ex3, R.raw.ex4, R.raw.ex5)
        val animationsList2 = listOf(R.raw.ex1, R.raw.ex2, R.raw.ex3, R.raw.ex1, R.raw.ex2)
        val animationsList3 =
            listOf(R.raw.ex5, R.raw.ex5, R.raw.ex3, R.raw.ex4, R.raw.ex5, R.raw.ex6)
        val animationsList4 =
            listOf(R.raw.ex2, R.raw.ex5, R.raw.ex6, R.raw.ex4, R.raw.ex5, R.raw.ex6)
        val animationsList5 =
            listOf(R.raw.ex3, R.raw.ex5, R.raw.ex6, R.raw.ex4, R.raw.ex5, R.raw.ex6)
        val animationsList6 =
            listOf(R.raw.ex4, R.raw.ex5, R.raw.ex6, R.raw.ex4, R.raw.ex5, R.raw.ex6)

        val myRecycler = RecyclerViewAdapterTrainings()
        viewModel.getTrainingsAnimation()

        viewModel.animList.onEach { value ->
            value?.let {
                myRecycler.updateData(it.data) { index ->
                    when (index) {
                        0 -> {
                            navController.navigate(
                                TrainingsFragmentDirections.actionTrainingsFragment2ToArmsWorkoutPlaneFragment(
                                    animationsList1.toIntArray()
                                )
                            )
                        }
                        1 -> {
                            navController.navigate(
                                TrainingsFragmentDirections.actionTrainingsFragment2ToArmsWorkoutPlaneFragment(
                                    animationsList2.toIntArray()
                                )
                            )
                        }
                        2 -> {
                            navController.navigate(
                                TrainingsFragmentDirections.actionTrainingsFragment2ToArmsWorkoutPlaneFragment(
                                    animationsList3.toIntArray()
                                )
                            )
                        }
                        3 -> {
                            navController.navigate(
                                TrainingsFragmentDirections.actionTrainingsFragment2ToArmsWorkoutPlaneFragment(
                                    animationsList4.toIntArray()
                                )
                            )
                        }
                        4 -> {
                            navController.navigate(
                                TrainingsFragmentDirections.actionTrainingsFragment2ToArmsWorkoutPlaneFragment(
                                    animationsList5.toIntArray()
                                )
                            )
                        }
                        5 -> {
                            navController.navigate(
                                TrainingsFragmentDirections.actionTrainingsFragment2ToArmsWorkoutPlaneFragment(
                                    animationsList6.toIntArray()
                                )
                            )
                        }
                    }
                }
                binding.progressBar.visibility = View.GONE
            }
        }.launchIn(lifecycleScope)
        binding.recyclerViewTrainings.adapter = myRecycler
        binding.recyclerViewTrainings.layoutManager = GridLayoutManager(context, 2)
    }
}
