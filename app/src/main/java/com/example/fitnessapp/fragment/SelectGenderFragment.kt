package com.example.fitnessapp.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentSelectGenderBinding

class SelectGenderFragment : Fragment() {
    lateinit var binding: FragmentSelectGenderBinding
    lateinit var navController: NavController
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectGenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences =
            (activity as MainActivity).getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navController = navHostFragment.navController
        binding.imgWoman.setOnClickListener {
            sharedPreferences.edit().putString(EDT_GENDER, "Female").apply()

            navController.navigate(R.id.action_selectGenderFragment_to_editInformationFragment)
        }
        binding.imgMan.setOnClickListener {
            sharedPreferences.edit().putString(EDT_GENDER, "Male").apply()

            navController.navigate(R.id.action_selectGenderFragment_to_editInformationFragment)
        }
    }
}