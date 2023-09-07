package com.kenig.shoppinglistcompose2023

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kenig.shoppinglistcompose2023.main_screen.MainScreen
import com.kenig.shoppinglistcompose2023.ui.theme.ShoppingListCompose2023Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint //всё что есть в AppModule теперь можно использовать в Активити
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListCompose2023Theme {
                MainScreen()
            }
        }
    }
}

