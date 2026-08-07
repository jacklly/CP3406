package com.example.assignment3.persistence

import android.content.Context
import androidx.room.Room
import kotlin.jvm.java

object DatabaseProvider {
    private var db: Database? = null

    fun getDatabase(context: Context): Database {
        return db ?: Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            "app_database"
        ).fallbackToDestructiveMigration(true)
            .build().also {
            db = it
        }
    }
}
