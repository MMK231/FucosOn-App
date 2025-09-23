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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.focusonapp.database.AppDatabase
import com.project.focusonapp.database.model.Task
import com.project.focusonapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val tasks: MutableList<Task> = mutableListOf()
    private val adapter = TasksAdapter(tasks)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRvViews()
        getDataFromDatabase()
    }

    private fun initRvViews() {
        binding.tasksRv.adapter = adapter
    }

    //override fun onResume() {
    //  super.onResume()
    //}

    private fun getDataFromDatabase() {
        context?.let {
            val tasks =
                AppDatabase
                    .createDatabase(it)
                    .tasksDao()
                    .getAllTasks()
            adapter.submitNewTasks(tasks.toMutableList())
        }

    }
}