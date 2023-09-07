package com.kenig.shoppinglistcompose2023.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.kenig.shoppinglistcompose2023.R
import com.kenig.shoppinglistcompose2023.navigation.NavigationGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNav(navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

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
        NavigationGraph(navController)
    }
}