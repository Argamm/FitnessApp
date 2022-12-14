package com.example.fitnessapp.fragment.navigation.trainings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.databinding.FragmentAbsBinding

class ABSFragment:Fragment() {
    lateinit var binding: FragmentAbsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAbsBinding.inflate(inflater, container, false)
        return binding.root
    }
}