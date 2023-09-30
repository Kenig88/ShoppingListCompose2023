package com.kenig.shoppinglistcompose2023.main_screen

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.kenig.shoppinglistcompose2023.ui.theme.BlueLight
import com.kenig.shoppinglistcompose2023.ui.theme.GrayLight

@Composable
fun BottomNav(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    val listItems = listOf(
        BottomNavItem.ListItem,
        BottomNavItem.NoteItem,
        BottomNavItem.SettingsItem
    )

    BottomNavigation(backgroundColor = Color.White) {
        listItems.forEach { bottomNavItem ->

            BottomNavigationItem(
                selected = currentRoute == bottomNavItem.route, //currentRoute - это экран который уже выбран
                onClick = {
                    onNavigate(bottomNavItem.route)
                },
                icon = {
                    Icon(painter = painterResource(bottomNavItem.iconId), contentDescription = null)
                },
                label = {
                    Text(text = bottomNavItem.title)
                },
                selectedContentColor = BlueLight,
                unselectedContentColor = GrayLight,
                alwaysShowLabel = false
            )
        }
    }
}