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

package com.project.focusonapp.UI.main.calender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.focusonapp.databinding.FragmentCalenderBinding

class CalenderFragment : Fragment() {
    lateinit var binding: FragmentCalenderBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}