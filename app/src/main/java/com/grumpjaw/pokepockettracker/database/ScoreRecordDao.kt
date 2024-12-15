package com.grumpjaw.pokepockettracker.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.grumpjaw.pokepockettracker.model.ScoreRecord

@Dao
interface ScoreRecordDao {
    @Insert
    suspend fun insert(scoreRecord: ScoreRecord)

    @Delete
    suspend fun delete(scoreRecord: ScoreRecord)
}
