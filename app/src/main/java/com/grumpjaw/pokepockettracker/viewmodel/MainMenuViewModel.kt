package com.grumpjaw.pokepockettracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grumpjaw.pokepockettracker.R

class MainMenuViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val _menuItems = MutableLiveData<List<String>>()
    val menuItems: LiveData<List<String>>
        get() = _menuItems

    init {
        loadMenuItems()
    }

    private fun loadMenuItems() {
        val menuItemsArray =
            getApplication<Application>().resources.getStringArray(R.array.menu_items)
        _menuItems.value = menuItemsArray.toList()
    }
}
