package com.kenig.shoppinglistcompose2023.settings_screen

sealed class SettingsEvent {
    data class OnItemSelected(val color: String) : SettingsEvent()
}
