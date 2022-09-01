package com.example.fitnessapp.fragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.fragment.navigation.customTrainings.Tasks

class CustomRecyclerViewAdapter(private var clickListener: (MutableList<Tasks>, Int) -> Unit) :
    RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>() {
    var task: MutableList<Tasks> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<Tasks>) {
        task.clear()
        task.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.model_tasks, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(task[position])
        viewHolder.itemView.setOnClickListener {
            clickListener(task, position)
        }
    }

    override fun getItemCount() = task.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tvEditTitle)
        private val tvDescription: TextView = view.findViewById(R.id.tvEditDescription)

        fun bind(user: Tasks) {
            tvTitle.text = user.taskLabel
            tvDescription.text = user.taskDescription
        }
    }
}