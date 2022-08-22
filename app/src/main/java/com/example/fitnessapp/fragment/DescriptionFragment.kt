package com.example.fitnessapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentDescriptionBinding
import kotlinx.android.synthetic.main.fragment_description.*

class DescriptionFragment : Fragment() {
    lateinit var binding: FragmentDescriptionBinding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        btn_next.setOnClickListener {
            navController.navigate(DescriptionFragmentDirections.actionDescriptionFragmentToLoginFragment())
        }
    }
}