package com.grumpjaw.pokepockettracker.repository

import com.grumpjaw.pokepockettracker.database.ScoreRecordDao
import com.grumpjaw.pokepockettracker.model.ScoreRecord

class ScoreRecordRepository(
    private val scoreRecordDao: ScoreRecordDao,
) {
    suspend fun insert(scoreRecord: ScoreRecord) {
        scoreRecordDao.insert(scoreRecord)
    }
}
