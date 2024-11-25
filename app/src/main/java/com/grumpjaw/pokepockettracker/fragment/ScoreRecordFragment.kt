package com.grumpjaw.pokepockettracker.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.grumpjaw.pokepockettracker.PokePocketTrackerApplication
import com.grumpjaw.pokepockettracker.R
import com.grumpjaw.pokepockettracker.database.GameRecord
import com.grumpjaw.pokepockettracker.viewmodel.GameRecordViewModel
import com.grumpjaw.pokepockettracker.viewmodel.GameRecordViewModelFactory

class ScoreRecordFragment : Fragment() {
    private val viewModel: GameRecordViewModel by viewModels {
        GameRecordViewModelFactory((requireActivity().application as PokePocketTrackerApplication).database)
    }

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
        val opponentDeckSpinner: Spinner = view.findViewById(R.id.opponent_deck_spinner)
        val firstTurnToggleButton: ToggleButton = view.findViewById(R.id.firstTurnToggleButton)
        val winToggleButton: ToggleButton = view.findViewById(R.id.winToggleButton)
        val saveButton: Button = view.findViewById(R.id.save_button)

        saveButton.setOnClickListener {
            val selectedDeck = myDeckSpinner.selectedItem.toString()
            val opponentDeck = opponentDeckSpinner.selectedItem.toString()
            val isFirstTurn = firstTurnToggleButton.isChecked
            val isWin = winToggleButton.isChecked

            val gameRecord =
                GameRecord(
                    playerDeck = selectedDeck,
                    opponentDeck = opponentDeck,
                    isFirstTurn = isFirstTurn,
                    isWin = isWin,
                    timestamp = System.currentTimeMillis(),
                )

            viewModel.insertRecord(gameRecord)

            Toast.makeText(requireContext(), getString(R.string.score_saved), Toast.LENGTH_SHORT).show()

            myDeckSpinner.setSelection(0)
            opponentDeckSpinner.setSelection(0)
            firstTurnToggleButton.isChecked = true
            winToggleButton.isChecked = true
        }
    }
}
