package com.grumpjaw.pokepockettracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
        setupActionBar()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setupActionBar() {
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp() || super.onSupportNavigateUp()
}
