package com.example.fitnessapp.fragment.navigation.myProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.databinding.FragmentMineProfileBinding

class MineProfileFragment : Fragment() {
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
        (activity as? MainActivity)?.let {
            with(binding) {
                tvEditName.text = it.userInfo.userName
                tvEditWeight.text = it.userInfo.userWeight
                tvEditTargetWeight.text = it.userInfo.userTargetWeight
                tvEditHeight.text = it.userInfo.userHeight
                tvEditAge.text = it.userInfo.userAge
                tvEditGender.text = it.userInfo.userGender
                tvEditEmail.text = it.userInfo.userEmail
            }
        }
    }
}