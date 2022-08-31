package com.example.fitnessapp.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.fitnessapp.R
import com.example.fitnessapp.fragment.navigation.customTrainings.Tasks

class RecyclerViewAdapterTrainings(
    var animList: List<Int?>,
    var textList: List<String>,
    private var clickListener: (List<Int>, Int) -> Unit
) : RecyclerView.Adapter<RecyclerViewAdapterTrainings.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lottieAnimator: LottieAnimationView = view.findViewById(R.id.lottieRecycler)
        val tvCountExercise: TextView = view.findViewById(R.id.tvCountExercises)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.model_trainings, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder) {
            animList[position]?.let { lottieAnimator.setAnimation(it) }
            tvCountExercise.text = textList[position]


        }

        viewHolder.itemView.setOnClickListener {
            clickListener(animList as List<Int>, position)
        }
    }

    override fun getItemCount(): Int = animList.size
}

