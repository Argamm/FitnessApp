package com.example.myfitnessapp.fragment.navigation.myProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import kotlinx.android.synthetic.main.fragment_rate_us.*


class RateUsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rate_us, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val totalStars = "Total Stars:: " + rating.numStars
        val ratingA = "Rating :: " + rating.rating


        btn_rate.setOnClickListener {
            Toast.makeText(activity,"""
                        $totalStars
                        $ratingA
                        """.trimIndent(), Toast.LENGTH_LONG
            ).show()
            btn_rate.isEnabled = false
            rating.isEnabled = false
        }
    }
}