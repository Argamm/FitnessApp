package com.example.fitnessapp.fragment.navigation.customTrainings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.databinding.FragmentCustomTrainBinding

class CustomTrainFragment:Fragment (){
    lateinit var binding: FragmentCustomTrainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomTrainBinding.inflate(inflater, container, false)
        return binding.root
    }

}