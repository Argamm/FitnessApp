package com.example.myfitnessapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.fitnessapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_description.*

class DescriptionFragment : Fragment() {
//    private val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
//    private val navController = navHostFragment.navController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_next.setOnClickListener {
            val selectGenderFragment = SelectGenderFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, selectGenderFragment)?.addToBackStack("selectGender")
                ?.commit()
//
//            navController.navigate(R.id.action_descriptionFragment_to_selectGenderFragment)
        }
    }
}