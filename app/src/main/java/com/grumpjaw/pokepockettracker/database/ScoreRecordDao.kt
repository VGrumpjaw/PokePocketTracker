package com.grumpjaw.pokepockettracker.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreRecordDao {
    @Query("SELECT * FROM score_records")
    fun getAll(): List<ScoreRecord>

    @Insert
    fun insertAll(vararg scoreRecords: ScoreRecord)

    @Delete
    fun delete(scoreRecord: ScoreRecord)
}
