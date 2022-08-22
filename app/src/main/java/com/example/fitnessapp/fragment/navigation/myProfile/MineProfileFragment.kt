package com.example.fitnessapp.fragment.navigation.myProfile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.databinding.FragmentMineProfileBinding
import com.example.fitnessapp.fragment.*

class MineProfileFragment : Fragment() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: FragmentMineProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMineProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences =
            (activity as MainActivity).getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

        with(binding) {
            tvEditName.text = sharedPreferences.getString(EDT_NAME, "non")
            tvEditWeight.text = sharedPreferences.getString(EDT_WEIGHT, "non")

            tvEditTargetWeight.text = sharedPreferences.getString(EDT_TARGET_WEIGHT, "non")
            tvEditGender.text = sharedPreferences.getString(EDT_GENDER, "non")

            tvEditHeight.text = sharedPreferences.getString(EDT_HEIGHT, "non")
            tvEditAge.text = sharedPreferences.getString(EDT_AGE, "non")
            tvEditEmail.text = sharedPreferences.getString(EDT_EMAIL, "non")
        }

    }
}