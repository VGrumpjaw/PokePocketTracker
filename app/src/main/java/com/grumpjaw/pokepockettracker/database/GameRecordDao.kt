package com.grumpjaw.pokepockettracker.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameRecordDao {
    @Insert
    suspend fun insert(gameRecord: GameRecord)

    @Query("SELECT * FROM game_records")
    suspend fun getAllRecords(): List<GameRecord>
}
