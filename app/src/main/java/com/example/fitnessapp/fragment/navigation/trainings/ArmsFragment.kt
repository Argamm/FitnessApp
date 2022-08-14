package com.example.fitnessapp.fragment.navigation.trainings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.databinding.FragmentArmsBinding

class ArmsFragment:Fragment() {
    lateinit var binding: FragmentArmsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArmsBinding.inflate(inflater, container, false)
        return binding.root
    }
}