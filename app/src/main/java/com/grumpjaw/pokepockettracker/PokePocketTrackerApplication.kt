package com.grumpjaw.pokepockettracker

import android.app.Application
import androidx.room.Room
import com.grumpjaw.pokepockettracker.database.AppDatabase

class PokePocketTrackerApplication : Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database =
            Room
                .databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    "app_database",
                ).build()
    }
}
