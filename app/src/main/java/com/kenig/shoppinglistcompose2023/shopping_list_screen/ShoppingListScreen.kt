package com.kenig.shoppinglistcompose2023.shopping_list_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenig.shoppinglistcompose2023.dialog.DialogController
import com.kenig.shoppinglistcompose2023.dialog.MainDialog

@Composable
fun ShoppingListScreen(viewModel: ShoppingListViewModel = hiltViewModel()) {
    Text(
        text = "Shopping list",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .wrapContentWidth()
    )
    MainDialog(dialogController = viewModel)
}