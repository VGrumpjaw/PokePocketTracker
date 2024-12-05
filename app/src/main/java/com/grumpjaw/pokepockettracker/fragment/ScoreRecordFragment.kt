package com.grumpjaw.pokepockettracker.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.grumpjaw.pokepockettracker.R
import com.grumpjaw.pokepockettracker.model.ScoreRecord
import com.grumpjaw.pokepockettracker.viewmodel.ScoreRecordViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScoreRecordFragment : Fragment() {
    private val viewModel: ScoreRecordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_score_record, container, false)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        val myDeckSpinner: Spinner = view.findViewById(R.id.my_deck_spinner)
        val grassCheckBox: CheckBox = view.findViewById(R.id.checkbox_grass)
        val fireCheckBox: CheckBox = view.findViewById(R.id.checkbox_fire)
        val waterCheckBox: CheckBox = view.findViewById(R.id.checkbox_water)
        val electricCheckBox: CheckBox = view.findViewById(R.id.checkbox_electric)
        val psychicCheckBox: CheckBox = view.findViewById(R.id.checkbox_psychic)
        val fightingCheckBox: CheckBox = view.findViewById(R.id.checkbox_fighting)
        val darkCheckBox: CheckBox = view.findViewById(R.id.checkbox_dark)
        val steelCheckBox: CheckBox = view.findViewById(R.id.checkbox_steel)
        val dragonCheckBox: CheckBox = view.findViewById(R.id.checkbox_dragon)
        val normalCheckBox: CheckBox = view.findViewById(R.id.checkbox_normal)
        val winCheckBox: CheckBox = view.findViewById(R.id.win_checkbox)
        val firstTurnCheckBox: CheckBox = view.findViewById(R.id.first_turn_checkbox)
        val saveButton: Button = view.findViewById(R.id.save_button)
        val clearButton: Button = view.findViewById(R.id.clear_button)

        saveButton.setOnClickListener {
            val selectedMyDeck = myDeckSpinner.selectedItem.toString()
            val containsGrassType = grassCheckBox.isChecked
            val containsWaterType = waterCheckBox.isChecked
            val containsFireType = fireCheckBox.isChecked
            val containsElectricType = electricCheckBox.isChecked
            val containsPsychicType = psychicCheckBox.isChecked
            val containsFightingType = fightingCheckBox.isChecked
            val containsDarkType = darkCheckBox.isChecked
            val containsSteelType = steelCheckBox.isChecked
            val containsDragonType = dragonCheckBox.isChecked
            val containsNormalType = normalCheckBox.isChecked
            val isWin = winCheckBox.isChecked
            val isFirstTurn = firstTurnCheckBox.isChecked

            val scoreRecord =
                ScoreRecord(
                    0,
                    selectedMyDeck,
                    containsGrassType,
                    containsWaterType,
                    containsFireType,
                    containsElectricType,
                    containsPsychicType,
                    containsFightingType,
                    containsDarkType,
                    containsSteelType,
                    containsDragonType,
                    containsNormalType,
                    isWin,
                    isFirstTurn,
                    System.currentTimeMillis(),
                )

            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    withContext(Dispatchers.IO) {
                        viewModel.insert(scoreRecord)
                    }
                    Toast.makeText(context, "戦績の登録が完了しました", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast
                        .makeText(
                            context,
                            "戦績の登録に失敗しました: ${e.message}",
                            Toast.LENGTH_SHORT,
                        ).show()
                }
            }
        }

        clearButton.setOnClickListener {
            grassCheckBox.isChecked = false
            fireCheckBox.isChecked = false
            waterCheckBox.isChecked = false
            electricCheckBox.isChecked = false
            psychicCheckBox.isChecked = false
            fightingCheckBox.isChecked = false
            darkCheckBox.isChecked = false
            steelCheckBox.isChecked = false
            dragonCheckBox.isChecked = false
            normalCheckBox.isChecked = false
            winCheckBox.isChecked = false
            firstTurnCheckBox.isChecked = false
        }
    }
}
