package com.example.fitnessapp.fragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.fitnessapp.R
import com.example.fitnessapp.userAniumationDatas.UserTrainingsData

class RecyclerViewAdapterTrainings :
    RecyclerView.Adapter<RecyclerViewAdapterTrainings.ViewHolder>() {
    var animList = ArrayList<UserTrainingsData>()
    var textList = ArrayList<String>()
    private var clickListener: (Int) -> Unit = { b -> }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(
        anList: ArrayList<UserTrainingsData>,
        clList: (Int) -> Unit
    ) {
        animList = anList
        clickListener = clList
        notifyDataSetChanged()
    }

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
            animList[position].anim?.toInt()?.let { lottieAnimator.setAnimation(it) }
            tvCountExercise.text = animList[position].text
        }

        viewHolder.itemView.setOnClickListener {
            clickListener(position)
        }
    }

    override fun getItemCount(): Int = animList.size
}

