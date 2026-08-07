package com.example.assignment3.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 2)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
}