package com.example.assignment3.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val id: Int = 1,
    val puuid: String,
    val username: String,
    val tier: String,
    val rank: String,
    val wins: Int,
    val losses: Int,
    val summonerLevel: Int,
    val summonerIcon: Int
)
