package com.example.myfitnessapp.fragment.navigation.myProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R

class MineProfileFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_mine_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        bottom_nav.visibility = View.GONE

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)//cuyc a talis het gnalu slaqy
    }
}