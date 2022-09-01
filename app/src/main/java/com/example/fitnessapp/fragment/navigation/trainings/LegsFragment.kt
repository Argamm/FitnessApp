package com.example.fitnessapp.fragment.navigation.trainings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.databinding.FragmentLegsBinding

class LegsFragment : Fragment() {
    lateinit var binding: FragmentLegsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLegsBinding.inflate(inflater, container, false)
        return binding.root
    }
}