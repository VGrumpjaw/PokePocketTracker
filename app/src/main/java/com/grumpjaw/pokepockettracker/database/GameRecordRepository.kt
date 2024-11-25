package com.grumpjaw.pokepockettracker.database

class GameRecordRepository(
    database: AppDatabase,
) {
    private val gameRecordDao = database.gameRecordDao()

    suspend fun insertRecord(gameRecord: GameRecord) {
        gameRecordDao.insert(gameRecord)
    }
}
