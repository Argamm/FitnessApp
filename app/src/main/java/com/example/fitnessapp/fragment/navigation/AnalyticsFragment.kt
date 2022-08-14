package com.example.fitnessapp.fragment.navigation

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.databinding.FragmentAnalyticsBinding
import kotlinx.android.synthetic.main.fragment_analytics.*

class AnalyticsFragment : Fragment() {
    lateinit var binding: FragmentAnalyticsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvCalendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = (dayOfMonth.toString() + "-" + (month + 1) + "-" + year)
            tvData.text = date
        }

        buttonSetCurrentWeight()
    }

    private fun buttonSetCurrentWeight() {
        binding.btnSetWeight.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Set ${edt_current_weight.text.toString()} Weight?")
                .setPositiveButton("Set") { _, _ ->
                    (activity as MainActivity).userInfo.userTargetWeight =
                        binding.edtCurrentWeight.text.toString()

                }.setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }.show()
        }
    }
}