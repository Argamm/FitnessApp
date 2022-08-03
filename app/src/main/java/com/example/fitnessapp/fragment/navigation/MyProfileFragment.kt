package com.example.myfitnessapp.fragment.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import com.example.myfitnessapp.fragment.navigation.myProfile.FeedbackFragment
import com.example.myfitnessapp.fragment.navigation.myProfile.MineProfileFragment
import com.example.myfitnessapp.fragment.navigation.myProfile.RateUsFragment
import com.example.myfitnessapp.fragment.navigation.myProfile.RemainderFragment
import kotlinx.android.synthetic.main.fragment_my_profile.*

class MyProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tv_my_profile.setOnClickListener {
            val mineProfileFragment = MineProfileFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, mineProfileFragment)?.addToBackStack("mineFragment")
                ?.commit()
        }

        tv_feedback.setOnClickListener {
            val feedbackFragment = FeedbackFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, feedbackFragment)?.addToBackStack("feedbackFragment")
                ?.commit()
        }

        tv_remainder.setOnClickListener {
            val remainderFragment = RemainderFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, remainderFragment)?.addToBackStack("remainderFragment")
                ?.commit()
        }

        tv_rate.setOnClickListener {
            val rateUsFragment = RateUsFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, rateUsFragment)?.addToBackStack("rateUsFragment")
                ?.commit()
        }
    }
}