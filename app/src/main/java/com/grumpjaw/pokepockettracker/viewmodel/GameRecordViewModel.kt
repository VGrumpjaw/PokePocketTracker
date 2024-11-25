package com.grumpjaw.pokepockettracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grumpjaw.pokepockettracker.database.AppDatabase
import com.grumpjaw.pokepockettracker.database.GameRecord
import com.grumpjaw.pokepockettracker.database.GameRecordRepository
import kotlinx.coroutines.launch

class GameRecordViewModel(
    database: AppDatabase,
) : ViewModel() {
    private val repository: GameRecordRepository = GameRecordRepository(database)

    fun insertRecord(gameRecord: GameRecord) {
        viewModelScope.launch {
            repository.insertRecord(gameRecord)
        }
    }
}
