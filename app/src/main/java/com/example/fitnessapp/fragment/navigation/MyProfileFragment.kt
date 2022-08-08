package com.example.myfitnessapp.fragment.navigation

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fitnessapp.R
import kotlinx.android.synthetic.main.fragment_my_profile.*

class MyProfileFragment : Fragment() {
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        tv_my_profile.setOnClickListener {
            navController.navigate(R.id.action_myProfileFragmentNav_to_mineProfileFragment)
        }

        tv_feedback.setOnClickListener {
            navController.navigate(R.id.action_myProfileFragmentNav_to_feedbackFragment)

        }

        tv_remainder.setOnClickListener {

            navController.navigate(R.id.action_myProfileFragmentNav_to_remainderFragment)

        }

        tv_rate.setOnClickListener {
            navController.navigate(R.id.action_myProfileFragmentNav_to_rateUsFragment)

        }

        tv_share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my App name `My Fitness App`. You can download it on PlayMarket")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        tv_communicate.setOnClickListener {
            navController.navigate(R.id.action_myProfileFragmentNav_to_communicateFragment)

        }
        tv_exit.setOnClickListener {
            AlertDialog.Builder(context).setTitle("Exit?").setPositiveButton("Ok") {_,_->
                activity?.finish()
            }.setNegativeButton("Cancel") {di, _->
                di.cancel()
            }.show()
        }
    }
}