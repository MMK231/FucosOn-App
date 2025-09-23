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

package com.project.focusonapp.UI.main.addToBottomSheet

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.focusonapp.database.AppDatabase
import com.project.focusonapp.database.model.Task
import com.project.focusonapp.databinding.AddToBottomSheetBinding


class AddToBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: AddToBottomSheetBinding
    var selectedDay = Calendar.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = AddToBottomSheetBinding.inflate(inflater, container, false)
        setCurrentDate()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPicker()
        binding.btnAdd.setOnClickListener {
            createTask()

        }

    }


    private fun createTask() {
        if (!isValidInputs()) return
        AppDatabase.createDatabase(requireContext()).tasksDao().createTask(
            Task(
                title = binding.titleTextInputLayout.editText?.text.toString(),
                description = binding.descriptionTextInputLayout.editText?.text.toString(),
                date = selectedDay.timeInMillis, status = false
            )
        )
        dismiss()

    }

    private fun initPicker() {
        binding.showDate.setOnClickListener {
            val dialog = DatePickerDialog(
                requireContext(),
                { p0, year, month, day ->
                    selectedDay.set(Calendar.YEAR, year)
                    selectedDay.set(Calendar.MONTH, month)
                    selectedDay.set(Calendar.DAY_OF_MONTH, day)
                    updateDate()
                },
                selectedDay.get(Calendar.YEAR),
                selectedDay.get(Calendar.MONTH),
                selectedDay.get(Calendar.DAY_OF_MONTH)
            )
            dialog.show()
        }

    }

    private fun setCurrentDate() {
        val currentDate = java.time.LocalDate.now()
        val formatter = java.time.format.DateTimeFormatter.ofPattern("dd / M / yyyy")
        binding.showDate.text = currentDate.format(formatter)
    }


    private fun updateDate() {
        val year = selectedDay.get(Calendar.YEAR)
        val month = selectedDay.get(Calendar.MONTH)
        val day = selectedDay.get(Calendar.DAY_OF_MONTH)
        binding.showDate.text = "$day / ${month + 1} / $year"
    }


    private fun isValidInputs(): Boolean {
        val title = binding.titleTextInputLayout.editText?.text
        val description = binding.descriptionTextInputLayout.editText?.text
        var isValid = true
        if (title.isNullOrBlank()) {
            binding.titleTextInputLayout.error = "Please Enter Valid Title"
            isValid = false
        } else {
            binding.titleTextInputLayout.error = null
        }
        if (description.isNullOrBlank()) {
            binding.descriptionTextInputLayout.error = "Please Enter Valid Description"
            isValid = false
        } else {
            binding.descriptionTextInputLayout.error = null
        }
        return isValid
    }
}
