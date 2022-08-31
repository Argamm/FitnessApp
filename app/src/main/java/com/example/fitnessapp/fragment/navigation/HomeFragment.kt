package com.example.fitnessapp.fragment.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fitnessapp.R
import com.example.fitnessapp.api.ApiService
import com.example.fitnessapp.databinding.FragmentHomeBinding
import com.example.fitnessapp.fragment.navigation.home.MyDialogFragment
import com.example.fitnessapp.userAniumationDatas.UserAnimationData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
            viewModel.list.collect(){
                viewModel.list.value?.let { it1 -> lottieClickHandler(it1) }
            }
        }
    }
/*
    private fun getInfoRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonkeeper.com/b/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiServer = retrofit.create(ApiService::class.java)
        val call: Call<ArrayList<UserAnimationData>> = apiServer.getData()

        call.enqueue(object : Callback<ArrayList<UserAnimationData>> {
            override fun onResponse(
                call: Call<ArrayList<UserAnimationData>?>,
                response: Response<ArrayList<UserAnimationData>?>
            ) {
                with(binding) {
                    if (response.isSuccessful) {
//                        progressBLoadingSecond.visibility = View.GONE
                        courseList = response.body()!!
                        lottieClickHandler(courseList)

                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<UserAnimationData>?>, t: Throwable) {
                Toast.makeText(context, "Fail to get the data..", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
    */

    private fun lottieClickHandler(courseList: ArrayList<UserAnimationData>) {
        with(binding) {
            lottieHealthyFood.setOnClickListener {
                val dialog = MyDialogFragment(courseList[0].anim1,getString(R.string.food_info) )
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
