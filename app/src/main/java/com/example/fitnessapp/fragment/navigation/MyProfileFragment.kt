package com.example.fitnessapp.fragment.navigation

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
import com.example.fitnessapp.databinding.FragmentMyProfileBinding

class MyProfileFragment : Fragment() {
    lateinit var binding: FragmentMyProfileBinding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        myProfileSectionsClickHandler()
    }

    private fun myProfileSectionsClickHandler() {
        with(binding) {
            tvMyProfile.setOnClickListener {
                navController.navigate(R.id.action_myProfileFragmentNav_to_mineProfileFragment)
            }
            tvFeedback.setOnClickListener {
                navController.navigate(R.id.action_myProfileFragmentNav_to_feedbackFragment)
            }
            tvRemainder.setOnClickListener {
                navController.navigate(R.id.action_myProfileFragmentNav_to_remainderFragment)
            }
            tvRate.setOnClickListener {
                navController.navigate(R.id.action_myProfileFragmentNav_to_rateUsFragment)
            }
            tvShare.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "This is my App name `My Fitness App`. You can download it on PlayMarket"
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
            tvCommunicate.setOnClickListener {
                navController.navigate(R.id.action_myProfileFragmentNav_to_communicateFragment)

            }
            tvExit.setOnClickListener {
                AlertDialog.Builder(context).setTitle(getString(R.string.exit_))
                    .setPositiveButton(getString(R.string.ok)) { _, _ ->
                        activity?.finish()
                    }.setNegativeButton(getString(R.string.cancel_)) { di, _ ->
                    di.cancel()
                }.show()
            }
            tvLanguage.setOnClickListener {
                navController.navigate(R.id.action_myProfileFragmentNav_to_languageFragment)
            }
        }
    }
}