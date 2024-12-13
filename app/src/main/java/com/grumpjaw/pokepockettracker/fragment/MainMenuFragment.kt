package com.grumpjaw.pokepockettracker.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grumpjaw.pokepockettracker.R
import com.grumpjaw.pokepockettracker.adapter.MenuAdapter
import com.grumpjaw.pokepockettracker.viewmodel.MainMenuViewModel

class MainMenuFragment : Fragment() {
    private val viewModel: MainMenuViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_main_menu, container, false)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        observeMenuItems()
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter =
            MenuAdapter { menuItem ->
                when (menuItem) {
                    getString(R.string.score_screen_text) -> findNavController().navigate(R.id.action_mainMenuFragment_to_scoreFragment)
                    // 他のメニュー項目の処理を追加
                }
            }
        recyclerView.adapter = adapter
    }

    private fun observeMenuItems() {
        viewModel.menuItems.observe(viewLifecycleOwner) { items ->
            (recyclerView.adapter as MenuAdapter).submitList(items)
        }
    }
}
