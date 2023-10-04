package com.kenig.shoppinglistcompose2023.shopping_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenig.shoppinglistcompose2023.R
import com.kenig.shoppinglistcompose2023.dialog.MainDialog
import com.kenig.shoppinglistcompose2023.ui.theme.GrayLight
import com.kenig.shoppinglistcompose2023.utils.UiEvent

@Composable
fun ShoppingListScreen(
    viewModel: ShoppingListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val itemsList =
        viewModel.list.collectAsState(initial = emptyList()) //collectAsState - получить как состояние и когда есть изменение список обновится

    LaunchedEffect(key1 = true) { //запускается 1 раз при запуске приложения
        viewModel.uiEvent.collect { uiEven -> //collect -> эта функция ждёт когда туда что либо передам
            when (uiEven) {
                is UiEvent.Navigate -> {
                    onNavigate(uiEven.route)
                }
                else -> {}
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayLight),
        contentPadding = PaddingValues(bottom = 50.dp)
    ) {
        items(itemsList.value) { items ->
            UiShoppingListItem(viewModel.titleColor.value, items) { event ->
                viewModel.onEvent(event)
            }
        }
    }
    MainDialog(dialogController = viewModel)

    if (itemsList.value.isEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .wrapContentWidth(),
            text = stringResource(R.string.item_empty),
            fontSize = 30.sp
        )
    }
}