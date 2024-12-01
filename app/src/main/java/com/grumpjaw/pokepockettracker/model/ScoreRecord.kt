package com.grumpjaw.pokepockettracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_records")
data class ScoreRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "my_deck")
    val myDeck: String,
    @ColumnInfo(name = "opponent_deck")
    val opponentDeck: String,
    @ColumnInfo(name = "has_won")
    val hasWon: Boolean,
    @ColumnInfo(name = "is_first")
    val isFirst: Boolean,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long,
)
