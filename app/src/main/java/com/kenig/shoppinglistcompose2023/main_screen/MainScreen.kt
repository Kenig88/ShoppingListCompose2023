package com.kenig.shoppinglistcompose2023.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kenig.shoppinglistcompose2023.R
import com.kenig.shoppinglistcompose2023.dialog.MainDialog
import com.kenig.shoppinglistcompose2023.navigation.NavigationGraph
import com.kenig.shoppinglistcompose2023.utils.Routes
import com.kenig.shoppinglistcompose2023.utils.UiEvent
import kotlinx.coroutines.flow.collect

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainNavHostController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(key1 = true) { //запускается 1 раз при запуске приложения
        viewModel.uiEvent.collect { uiEven -> //collect -> эта функция ждёт когда туда что либо передам
            when (uiEven) {
                is UiEvent.MainNavigate -> {
                    mainNavHostController.navigate(uiEven.route)
                }
                is UiEvent.Navigate -> {
                    navController.navigate(uiEven.route)
                }
                else -> {}
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomNav(currentRoute) { route ->
                viewModel.onEvent(MainScreenEvent.Navigate(route))
            }
        },
        floatingActionButton = {
            if (viewModel.showFloatingButton.value)
                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(
                            MainScreenEvent.OnNewItemClick(
                                currentRoute ?: Routes.SHOPPING_LIST
                            )
                        )
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = Color.White
                    )
                }
        },
        floatingActionButtonPosition = FabPosition.End, //расположение FAB на экране (центер, конец и тд)
        isFloatingActionButtonDocked = false //расположение FAB между bottom-menu и экраном
    ) {
        NavigationGraph(navController) { route ->
            viewModel.onEvent(MainScreenEvent.MainNavigate(route))
        }
        MainDialog(dialogController = viewModel)
    }
}