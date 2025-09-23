/*
 *
 *  * Copyright (c) 2025 Mohammed Mahmoud
 *  *
 *  * This file is part of FocusOn project.
 *  *
 *  * GitHub: https://github.com/MMK231
 *  *
 *  * All rights reserved. Unauthorized copying of this file,
 *  * via any medium is strictly prohibited.
 *
 */

package com.project.focusonapp.UI.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.focusonapp.database.model.Task
import com.project.focusonapp.databinding.TaskItemBinding

class TasksAdapter(var tasks: MutableList<Task>) :
    RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = TaskItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val task = tasks?.get(position)
        holder.binding.title.text = (task?.title)
        holder.binding.taskDescription.text = "${task?.description}"
    }

    override fun getItemCount(): Int = tasks?.size ?: 0


    fun submitNewTasks(newTasks: MutableList<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }

    fun addItem(newTask: Task) {
        tasks!!.add(newTask)
        notifyItemInserted(tasks!!.size)
    }


    class ViewHolder(var binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}