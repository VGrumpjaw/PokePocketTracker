package com.grumpjaw.pokepockettracker.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
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
    private var lastInsertedScoreRecord: ScoreRecord? = null

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
        setView(view)
    }

    private fun setView(view: View) {
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
        val winRadioButton: RadioButton = view.findViewById(R.id.radio_win)
        val loseRadioButton: RadioButton = view.findViewById(R.id.radio_lose)
        val firstTurnRadioButton: RadioButton = view.findViewById(R.id.radio_first_turn)
        val secondTurnRadioButton: RadioButton = view.findViewById(R.id.radio_second_turn)
        val saveButton: Button = view.findViewById(R.id.save_button)
        val clearButton: Button = view.findViewById(R.id.clear_button)
        val undoButton: Button = view.findViewById(R.id.undo_button)

        saveButton.setOnClickListener {
            if (validateInput(
                    winRadioButton,
                    loseRadioButton,
                    firstTurnRadioButton,
                    secondTurnRadioButton,
                )
            ) {
                insertScoreRecord(
                    myDeckSpinner,
                    grassCheckBox,
                    fireCheckBox,
                    waterCheckBox,
                    electricCheckBox,
                    psychicCheckBox,
                    fightingCheckBox,
                    darkCheckBox,
                    steelCheckBox,
                    dragonCheckBox,
                    normalCheckBox,
                    winRadioButton,
                    firstTurnRadioButton,
                )
            } else {
                Toast
                    .makeText(
                        context,
                        getString(R.string.select_turn_and_outcome),
                        Toast.LENGTH_SHORT,
                    ).show()
            }
        }

        clearButton.setOnClickListener {
            clearSelection(
                winRadioButton,
                loseRadioButton,
                firstTurnRadioButton,
                secondTurnRadioButton,
                grassCheckBox,
                fireCheckBox,
                waterCheckBox,
                electricCheckBox,
                psychicCheckBox,
                fightingCheckBox,
                darkCheckBox,
                steelCheckBox,
                dragonCheckBox,
                normalCheckBox,
            )
        }

        undoButton.setOnClickListener {
            lastInsertedScoreRecord?.let { scoreRecord ->
                viewLifecycleOwner.lifecycleScope.launch {
                    try {
                        withContext(Dispatchers.IO) {
                            viewModel.delete(scoreRecord)
                            lastInsertedScoreRecord = null
                        }
                        Toast
                            .makeText(context, getText(R.string.undo_success), Toast.LENGTH_SHORT)
                            .show()
                    } catch (e: Exception) {
                        Toast
                            .makeText(
                                context,
                                getString(R.string.undo_failure, e.message),
                                Toast.LENGTH_SHORT,
                            ).show()
                    }
                }
            } ?: run {
                Toast
                    .makeText(context, getText(R.string.no_record_to_undo), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun clearSelection(
        winRadioButton: RadioButton,
        loseRadioButton: RadioButton,
        firstTurnRadioButton: RadioButton,
        secondTurnRadioButton: RadioButton,
        vararg checkBoxes: CheckBox,
    ) {
        winRadioButton.isChecked = false
        loseRadioButton.isChecked = false
        firstTurnRadioButton.isChecked = false
        secondTurnRadioButton.isChecked = false
        checkBoxes.forEach { it.isChecked = false }
    }

    private fun validateInput(
        winRadioButton: RadioButton,
        loseRadioButton: RadioButton,
        firstTurnRadioButton: RadioButton,
        secondTurnRadioButton: RadioButton,
    ): Boolean =
        (winRadioButton.isChecked || loseRadioButton.isChecked) &&
            (firstTurnRadioButton.isChecked || secondTurnRadioButton.isChecked)

    private fun insertScoreRecord(
        myDeckSpinner: Spinner,
        grassCheckBox: CheckBox,
        fireCheckBox: CheckBox,
        waterCheckBox: CheckBox,
        electricCheckBox: CheckBox,
        psychicCheckBox: CheckBox,
        fightingCheckBox: CheckBox,
        darkCheckBox: CheckBox,
        steelCheckBox: CheckBox,
        dragonCheckBox: CheckBox,
        normalCheckBox: CheckBox,
        winRadioButton: RadioButton,
        firstTurnRadioButton: RadioButton,
    ) {
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
        val isWin = winRadioButton.isChecked
        val isFirstTurn = firstTurnRadioButton.isChecked

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
                    lastInsertedScoreRecord = scoreRecord
                }
                Toast
                    .makeText(context, getText(R.string.score_record_success), Toast.LENGTH_SHORT)
                    .show()
            } catch (e: Exception) {
                Toast
                    .makeText(
                        context,
                        getString(R.string.score_record_failure, e.message),
                        Toast.LENGTH_SHORT,
                    ).show()
            }
        }
    }
}
