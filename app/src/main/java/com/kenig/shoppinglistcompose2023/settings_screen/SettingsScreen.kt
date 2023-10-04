package com.kenig.shoppinglistcompose2023

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenig.shoppinglistcompose2023.settings_screen.SettingsViewModel
import com.kenig.shoppinglistcompose2023.settings_screen.UiColorItem
import com.kenig.shoppinglistcompose2023.ui.theme.LightText

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val list = viewModel.colorItemListState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = stringResource(R.string.settings_title_color),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(R.string.settings_description_color),
            fontSize = 18.sp,
            color = LightText
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            items(list) { item ->
                UiColorItem(item) { event ->
                    viewModel.onEvent(event)
                }
            }
        }
    }
}
