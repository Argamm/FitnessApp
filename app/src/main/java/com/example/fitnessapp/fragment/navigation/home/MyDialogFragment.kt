package com.example.fitnessapp.fragment.navigation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentCommunicateBinding
import com.example.fitnessapp.databinding.FragmentDialogBinding
import com.example.fitnessapp.fragment.navigation.IMG
import com.example.fitnessapp.fragment.navigation.INFO
import com.example.fitnessapp.fragment.navigation.NAME
import kotlinx.android.synthetic.main.fragment_dialog.*

class MyDialogFragment : DialogFragment() {
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

        arguments?.getInt(IMG)?.let { binding.lottieRandom.setAnimation(it) }

        binding.tvRandom.text = arguments?.getString(INFO)
//        when {
//            arguments?.getString(NAME) == "food" ->  lottieRandom.setAnimation(R.raw.walking_avocado)
//            arguments?.getString(NAME) == "sleep" ->  lottieRandom.setAnimation(R.raw.sleep)
//            arguments?.getString(NAME) == "water" ->  lottieRandom.setAnimation(R.raw.water)
//        }

        binding.floatingActionButton.setOnClickListener {
            dialog?.dismiss()

//            dialog?.onBackPressed()
        }
    }
    
}