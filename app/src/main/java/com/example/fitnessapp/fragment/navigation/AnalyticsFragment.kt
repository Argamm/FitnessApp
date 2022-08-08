package com.example.myfitnessapp.fragment.navigation

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.R
import kotlinx.android.synthetic.main.fragment_analytics.*

class AnalyticsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_analytics, container, false)
    }

    //    lateinit var dateTV: TextView
//    lateinit var calendarView: CalendarView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        dateTV = activity?.findViewById(R.id.tvData)!!
//        calendarView = activity?.findViewById(R.id.tvCalendar)!!

        tvCalendar
            .setOnDateChangeListener { view, year, month, dayOfMonth ->
                val date = (dayOfMonth.toString() + "-" + (month + 1) + "-" + year)
                tvData.text = date
            }
        btn_set_weight.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Set ${edt_current_weight.text.toString()} Weight?")
                .setPositiveButton("Set") { di, _ ->
                    (activity as MainActivity).let {
                        it.userInfo.userTargetWeight = edt_current_weight.text.toString()
                    }

                }.setNegativeButton("Cancel") { di, _ ->
                    di.cancel()
                }.show()
        }
//        }
    }
}