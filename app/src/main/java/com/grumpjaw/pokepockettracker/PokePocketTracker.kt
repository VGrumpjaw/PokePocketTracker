package com.grumpjaw.pokepockettracker

import android.app.Application
import androidx.room.Room
import com.grumpjaw.pokepockettracker.database.AppDatabase

class PokePocketTracker : Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database =
            Room
                .databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    "poke_pocket_tracker_db",
                ).build()
    }
}
