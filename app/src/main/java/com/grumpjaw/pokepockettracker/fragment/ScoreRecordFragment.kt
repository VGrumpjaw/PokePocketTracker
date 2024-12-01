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
        val opponentDeckSpinner: Spinner = view.findViewById(R.id.opponent_deck_spinner)
        val winCheckBox: CheckBox = view.findViewById(R.id.win_checkbox)
        val firstTurnCheckBox: CheckBox = view.findViewById(R.id.first_turn_checkbox)
        val saveButton: Button = view.findViewById(R.id.save_button)

        saveButton.setOnClickListener {
            val selectedMyDeck = myDeckSpinner.selectedItem.toString()
            val selectedOpponentDeck = opponentDeckSpinner.selectedItem.toString()
            val hasWon = winCheckBox.isChecked
            val hasFirstTurn = firstTurnCheckBox.isChecked
            val scoreRecord =
                ScoreRecord(
                    myDeck = selectedMyDeck,
                    opponentDeck = selectedOpponentDeck,
                    hasWon = hasWon,
                    isFirst = hasFirstTurn,
                    timestamp = System.currentTimeMillis(),
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
    }
}
