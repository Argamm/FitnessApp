package com.example.fitnessapp.fragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.databinding.ModelTasksBinding
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
        val modelTasksBinding: ModelTasksBinding  = ModelTasksBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(modelTasksBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(task[position])
        viewHolder.itemView.setOnClickListener {
            clickListener(task, position)
        }
    }

    override fun getItemCount() = task.size

    class ViewHolder(private val view: ModelTasksBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(user: Tasks) {
            view.tvEditTitle.text = user.taskLabel
            view.tvEditDescription.text = user.taskDescription
        }
    }
}