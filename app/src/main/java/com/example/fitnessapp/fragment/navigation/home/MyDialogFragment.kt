package com.example.fitnessapp.fragment.navigation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.fitnessapp.R
import com.example.fitnessapp.fragment.navigation.IMG
import com.example.fitnessapp.fragment.navigation.INFO
import com.example.fitnessapp.fragment.navigation.NAME
import kotlinx.android.synthetic.main.fragment_dialog.*

class MyDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt(IMG)?.let { lottieRandom.setAnimation(it) }

        tvRandom.text = arguments?.getString(INFO)
//        when {
//            arguments?.getString(NAME) == "food" ->  lottieRandom.setAnimation(R.raw.walking_avocado)
//            arguments?.getString(NAME) == "sleep" ->  lottieRandom.setAnimation(R.raw.sleep)
//            arguments?.getString(NAME) == "water" ->  lottieRandom.setAnimation(R.raw.water)
//        }


        floatingActionButton.setOnClickListener {
            dialog?.dismiss()

//            dialog?.onBackPressed()
        }
    }
}