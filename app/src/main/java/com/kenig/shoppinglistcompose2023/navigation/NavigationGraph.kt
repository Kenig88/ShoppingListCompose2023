package com.kenig.shoppinglistcompose2023.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kenig.shoppinglistcompose2023.SettingsScreen
import com.kenig.shoppinglistcompose2023.note_list_screen.NoteListScreen
import com.kenig.shoppinglistcompose2023.shopping_list_screen.ShoppingListScreen
import com.kenig.shoppinglistcompose2023.utils.Routes

@Composable
fun NavigationGraph(navController: NavHostController, onNavigate: (String) -> Unit) {
    NavHost(
        navController = navController,
        startDestination = Routes.SHOPPING_LIST
    ) {
        composable(Routes.SHOPPING_LIST) {
            ShoppingListScreen() { route ->
                onNavigate(route)
            }
        }
        composable(Routes.NOTE_LIST) {
            NoteListScreen() { route ->
                onNavigate(route)
            }
        }
        composable(Routes.SETTINGS) {
            SettingsScreen()
        }
    }
}