package com.grumpjaw.pokepockettracker.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_records")
data class GameRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val playerDeck: String,
    val opponentDeck: String,
    val isFirstTurn: Boolean,
    val isWin: Boolean,
    val timestamp: Long,
)
