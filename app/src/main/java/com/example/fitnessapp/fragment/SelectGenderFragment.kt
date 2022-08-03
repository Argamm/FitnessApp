package com.example.myfitnessapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import kotlinx.android.synthetic.main.fragment_select_gender.*

class SelectGenderFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_select_gender, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        img_woman.setOnClickListener {
            val editInformationFragment = EditInformationFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, editInformationFragment)?.addToBackStack("editInfo")
                ?.commit()
        }
        img_man.setOnClickListener {
            val editInformationFragment = EditInformationFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, editInformationFragment)?.addToBackStack("editInfo")
                ?.commit()
        }
    }
}