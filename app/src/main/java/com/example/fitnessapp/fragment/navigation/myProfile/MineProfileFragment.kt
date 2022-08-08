package com.example.fitnessapp.fragment.navigation.myProfile

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.UserInfo
import com.example.myfitnessapp.fragment.AGE
import com.example.myfitnessapp.fragment.HEIGHT
import com.example.myfitnessapp.fragment.NAME
import com.example.myfitnessapp.fragment.WEIGHT
import kotlinx.android.synthetic.main.fragment_mine_profile.*

class MineProfileFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mine_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        bottom_nav.visibility = View.GONE

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)//cuyc a talis het gnalu slaqy
//        tv_edit_name.text = arguments?.getString(NAME, "defName")
//        tv_edit_weight.text = arguments?.getString(WEIGHT, "deWeight")
//        tv_edit_height.text = arguments?.getString(HEIGHT, "defHeight")
//        tv_edit_age.text = arguments?.getString(AGE)
//        tv_edit_gender.text = arguments?.getString("gender")
        (activity as? MainActivity)?.let {
            tv_edit_name.text = it.userInfo._name
            tv_edit_weight.text = it.userInfo._weight
            tv_edit_target_weight.text = it.userInfo._weight
            tv_edit_height.text = it.userInfo._height
            tv_edit_age.text = it.userInfo._age
            tv_edit_gender.text = it.userInfo._gender
        }
//        tv_edit_gender.text = arguments?.getString("gender")
    }
}