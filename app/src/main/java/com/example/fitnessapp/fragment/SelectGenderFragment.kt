package com.example.myfitnessapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.fragment.navigation.myProfile.MineProfileFragment
import kotlinx.android.synthetic.main.fragment_select_gender.*

class SelectGenderFragment:Fragment() {
//    private val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
//    private val navController = navHostFragment.navController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_select_gender, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mineProfileFragment = MineProfileFragment()
        val args = Bundle()

        img_woman.setOnClickListener {
            args.putString("gender", "Woman")
            mineProfileFragment.arguments = args
            val editInformationFragment = EditInformationFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, editInformationFragment)?.addToBackStack("editInfo")
                ?.commit()
//            navController.navigate(R.id.action_selectGenderFragment_to_editInformationFragment)
        }
        img_man.setOnClickListener {
//            args.putString("gender", "Man")
//            mineProfileFragment.arguments = args
            (activity as? MainActivity)?.let {
                it.userInfo._gender = "Man"
            }
            val editInformationFragment = EditInformationFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, editInformationFragment)?.addToBackStack("editInfo")
                ?.commit()
//            navController.navigate(R.id.action_selectGenderFragment_to_editInformationFragment)

        }
    }
}