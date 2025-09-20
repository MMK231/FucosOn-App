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

package com.project.focusonapp.UI.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.project.focusonapp.R
import com.project.focusonapp.UI.main.addToBottomSheet.AddToBottomSheet
import com.project.focusonapp.UI.main.calender.CalenderFragment
import com.project.focusonapp.UI.main.home.HomeFragment
import com.project.focusonapp.UI.main.pomodoro.PomodoroFragment
import com.project.focusonapp.UI.main.settings.SettingsFragment
import com.project.focusonapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navigationButtonsMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    showFragment(HomeFragment())
                }

                R.id.calender -> {
                    showFragment(CalenderFragment())
                }

                R.id.pomodoro -> {
                    showFragment(PomodoroFragment())
                }

                R.id.settings -> {
                    showFragment(SettingsFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.navigationButtonsMenu.selectedItemId = R.id.home

        binding.fabAddTask.setOnClickListener {
            val addToBottomSheet = AddToBottomSheet()
            addToBottomSheet.show(supportFragmentManager, null)
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }


}