package com.grumpjaw.pokepockettracker.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ScoreRecord::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scoreRecordDao(): ScoreRecordDao
}