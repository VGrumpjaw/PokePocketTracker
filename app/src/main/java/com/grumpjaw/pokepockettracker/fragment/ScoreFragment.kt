package com.grumpjaw.pokepockettracker.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grumpjaw.pokepockettracker.R
import com.grumpjaw.pokepockettracker.adapter.MenuAdapter

class ScoreFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_score, container, false)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter =
            MenuAdapter { menuItem ->
                when (menuItem) {
                    getString(R.string.score_record_text) -> findNavController().navigate(R.id.action_scoreFragment_to_scoreRecordFragment)
                    getString(
                        R.string.score_display_text,
                    ),
                    -> findNavController().navigate(R.id.action_scoreFragment_to_scoreDisplayFragment)
                }
            }
        recyclerView.adapter = adapter

        val menuItems = resources.getStringArray(R.array.score_menu_items).toList()
        adapter.submitList(menuItems)
    }
}
