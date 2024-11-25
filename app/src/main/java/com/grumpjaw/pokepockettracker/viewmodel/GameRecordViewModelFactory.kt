package com.grumpjaw.pokepockettracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grumpjaw.pokepockettracker.database.AppDatabase

class GameRecordViewModelFactory(
    private val database: AppDatabase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameRecordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameRecordViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
