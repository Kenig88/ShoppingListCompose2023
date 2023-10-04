package com.kenig.shoppinglistcompose2023.note_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenig.shoppinglistcompose2023.dialog.MainDialog
import com.kenig.shoppinglistcompose2023.ui.theme.GrayLight
import com.kenig.shoppinglistcompose2023.ui.theme.LightText
import com.kenig.shoppinglistcompose2023.utils.UiEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val itemsList = viewModel.noteList.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect() { uiEven ->
            when (uiEven) {
                is UiEvent.Navigate -> {
                    onNavigate(uiEven.route)
                }
                is UiEvent.ShowSnackBar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEven.message,
                        actionLabel = "Undone"
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(NoteListEvent.UndoneDeleteItem)
                    }
                }
                else -> {}
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(hostState = scaffoldState.snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    modifier = Modifier.padding(bottom = 120.dp)
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayLight)
        ) {
            items(itemsList.value) { item ->
                UiNoteItem(viewModel.titleColor.value, item) { event ->
                    viewModel.onEvent(event)
                }
            }
        }
        MainDialog(viewModel)
        if (itemsList.value.isEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(),
                text = "Empty",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color = LightText
            )
        }
    }
}