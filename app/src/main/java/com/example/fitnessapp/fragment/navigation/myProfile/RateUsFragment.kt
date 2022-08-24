package com.example.fitnessapp.fragment.navigation.myProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentRateUsBinding


class RateUsFragment : Fragment() {
    lateinit var binding: FragmentRateUsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRateUsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val totalStars = getString(R.string.totalStars) + binding.rating.numStars
        val ratingA = getString(R.string.rating) + binding.rating.rating


        binding.btnRate.setOnClickListener {
            Toast.makeText(activity,"""
                        $totalStars
                        $ratingA
                        """.trimIndent(), Toast.LENGTH_LONG
            ).show()
            binding.btnRate.isEnabled = false
            binding.rating.isEnabled = false
        }
    }
}