package com.kenig.shoppinglistcompose2023.settings_screen

import androidx.compose.ui.graphics.Color
import com.kenig.shoppinglistcompose2023.ui.theme.*

object ColorsUtils {
    val colorsList = listOf(
        "#FF000000",
        "#FFC51162",
        "#FFAA00FF",
        "#FF6200EA",
        "#FF304FFE",
        "#FF2962FF",
        "#FF0091EA",
        "#FF00B8D4",
        "#FF00BFA5",
        "#FF00C853",
        "#FF64DD17",
        "#FFAEEA00",
        "#FFFFD600",
        "#FFFFAB00",
        "#FFFF6D00",
        "#FFDD2C00"
    )

    fun getProgressColor(progress: Float): Color {
        return when (progress) {
            in 0.0..0.339 -> RedProgress
            in 0.34..0.669 -> OrangeProgress
            in 0.67..1.0 -> GreenProgress
            else -> Color.Black
        }
    }
}