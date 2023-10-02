package com.kenig.shoppinglistcompose2023.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kenig.shoppinglistcompose2023.add_item_screen.AddItemScreen
import com.kenig.shoppinglistcompose2023.main_screen.MainScreen
import com.kenig.shoppinglistcompose2023.new_note_screen.NewNoteScreen
import com.kenig.shoppinglistcompose2023.utils.Routes

@Composable
fun MainNavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN) {
        composable(Routes.MAIN_SCREEN) {
            MainScreen(navController)
        }
        composable(Routes.ADD_ITEM + "/{listId}") {  //{listId} -> получаю конкретный item из UiShopList
            AddItemScreen()
        }
        composable(Routes.NEW_NOTE + "/{noteId}") {
            NewNoteScreen() {
                navController.popBackStack() //вернусь на экран котором был ранее
            }
        }
    }
}