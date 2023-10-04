package com.kenig.shoppinglistcompose2023.settings_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenig.shoppinglistcompose2023.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    val colorItemListState = mutableStateOf<List<ColorItem>>(emptyList())

    init {
        viewModelScope.launch {
            dataStoreManager.getStringPreference(
                DataStoreManager.TITLE_COLOR,
                "#FF000000"
            ).collect { selectedColor ->
                val tempColorItemList = ArrayList<ColorItem>()

                ColorsUtils.colorsList.forEach { color ->
                    tempColorItemList.add(
                        ColorItem(
                            color,
                            selectedColor == color
                        )
                    )
                }
                colorItemListState.value = tempColorItemList
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnItemSelected -> {
                viewModelScope.launch {
                    dataStoreManager.saveStringPreference(
                        event.color,
                        DataStoreManager.TITLE_COLOR
                    )
                }
            }
        }
    }
}