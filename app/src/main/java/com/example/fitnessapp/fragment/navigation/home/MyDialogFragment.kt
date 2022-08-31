package com.example.fitnessapp.fragment.navigation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.fitnessapp.databinding.FragmentDialogBinding

class MyDialogFragment(val img: String?, val str: String) : DialogFragment() {
    lateinit var binding: FragmentDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        img?.toInt()?.let { binding.lottieRandom.setAnimation(it) }
        binding.tvRandom.text = str

        binding.floatingActionButton.setOnClickListener {
            dialog?.dismiss()
        }
    }
}