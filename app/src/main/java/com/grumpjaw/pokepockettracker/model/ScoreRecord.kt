package com.grumpjaw.pokepockettracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_records")
data class ScoreRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "my_deck")
    val myDeck: String,
    @ColumnInfo(name = "contains_grass_type")
    val containsGrassType: Boolean,
    @ColumnInfo(name = "contains_water_type")
    val containsWaterType: Boolean,
    @ColumnInfo(name = "contains_fire_type")
    val containsFireType: Boolean,
    @ColumnInfo(name = "contains_electric_type")
    val containsElectricType: Boolean,
    @ColumnInfo(name = "contains_psychic_type")
    val containsPsychicType: Boolean,
    @ColumnInfo(name = "contains_fighting_type")
    val containsFightingType: Boolean,
    @ColumnInfo(name = "contains_dark_type")
    val containsDarkType: Boolean,
    @ColumnInfo(name = "contains_steel_type")
    val containsSteelType: Boolean,
    @ColumnInfo(name = "contains_dragon_type")
    val containsDragonType: Boolean,
    @ColumnInfo(name = "contains_normal_type")
    val containsNormalType: Boolean,
    @ColumnInfo(name = "is_win")
    val isWin: Boolean,
    @ColumnInfo(name = "is_first_turn")
    val isFirstTurn: Boolean,
    @ColumnInfo(name = "createAt")
    val create: Long,
)
