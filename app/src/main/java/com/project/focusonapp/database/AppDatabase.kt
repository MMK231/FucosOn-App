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

package com.project.focusonapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.focusonapp.database.dao.TasksDao
import com.project.focusonapp.database.model.Task


@Database(entities = [Task::class], version = 2, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao

    companion object {
        private var db: AppDatabase? = null
        private const val dbName: String = "Task-database"

        fun createDatabase(context: Context): AppDatabase {
            if (db == null) {
                db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, dbName
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return db!!
        }
    }
}