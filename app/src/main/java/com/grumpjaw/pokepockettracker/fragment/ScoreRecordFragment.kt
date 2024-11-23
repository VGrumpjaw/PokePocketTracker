package com.grumpjaw.pokepockettracker.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import com.grumpjaw.pokepockettracker.R

class ScoreRecordFragment : Fragment() {
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
        val winLossRadioGroup: RadioGroup = view.findViewById(R.id.win_lose_radio_group)
        val saveButton: Button = view.findViewById(R.id.save_button)

        saveButton.setOnClickListener {
            val selectedDeck = myDeckSpinner.selectedItem.toString()
            val winLose =
                when (winLossRadioGroup.checkedRadioButtonId) {
                    R.id.win_radio_button -> "勝ち"
                    R.id.lose_radio_button -> "負け"
                    else -> "未選択"
                }

            val opponentDecks = mutableListOf<String>()
            for (i in 1..8) {
                val resId = resources.getIdentifier("deck_button_$i", "id", context?.packageName)
                val button = view.findViewById<ToggleButton>(resId)
                if (button.isChecked) {
                    opponentDecks.add(button.text.toString())
                }
            }

            // ここで記録を保存する処理を追加
            // 例えば、データベースやSharedPreferencesに保存するなど
        }
    }
}
