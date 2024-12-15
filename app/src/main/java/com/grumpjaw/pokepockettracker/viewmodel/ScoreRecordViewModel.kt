package com.grumpjaw.pokepockettracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.grumpjaw.pokepockettracker.PokePocketTracker
import com.grumpjaw.pokepockettracker.model.ScoreRecord
import com.grumpjaw.pokepockettracker.repository.ScoreRecordRepository
import kotlinx.coroutines.launch

class ScoreRecordViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val repository: ScoreRecordRepository

    init {
        val app = application as PokePocketTracker
        val scoreRecordDao = app.database.scoreRecordDao()
        repository = ScoreRecordRepository(scoreRecordDao)
    }

    fun insert(scoreRecord: ScoreRecord) =
        viewModelScope.launch {
            repository.insert(scoreRecord)
        }

    fun delete(scoreRecord: ScoreRecord) {
        viewModelScope.launch {
            repository.delete(scoreRecord)
        }
    }
}
