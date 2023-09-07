package com.kenig.shoppinglistcompose2023.main_screen

import com.kenig.shoppinglistcompose2023.R
import com.kenig.shoppinglistcompose2023.utils.Routes

sealed class BottomNavItem(val title: String, val iconId: Int, val route: String) {
    object ListItem : BottomNavItem("List", R.drawable.ic_list, Routes.SHOPPING_LIST)
    object NoteItem : BottomNavItem("Note", R.drawable.ic_note, Routes.NOTE_LIST)
    object SettingsItem : BottomNavItem("Settings", R.drawable.ic_settings, Routes.SETTINGS)
}