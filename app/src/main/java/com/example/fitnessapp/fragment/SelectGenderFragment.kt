package com.example.myfitnessapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.R
import kotlinx.android.synthetic.main.fragment_select_gender.*

class SelectGenderFragment : Fragment() {
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_gender, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navController = navHostFragment.navController
        img_woman.setOnClickListener {
            (activity as? MainActivity)?.let {
                it.userInfo.userGender = "Woman"
            }
            navController.navigate(R.id.action_selectGenderFragment_to_editInformationFragment)
        }
        img_man.setOnClickListener {
            (activity as? MainActivity)?.let {
                it.userInfo.userGender = "Man"
            }
            navController.navigate(R.id.action_selectGenderFragment_to_editInformationFragment)

        }
    }
}