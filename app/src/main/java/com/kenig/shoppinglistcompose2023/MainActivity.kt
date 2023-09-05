package com.kenig.shoppinglistcompose2023

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kenig.shoppinglistcompose2023.ui.theme.ShoppingListCompose2023Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListCompose2023Theme {
            }
        }
    }
}

