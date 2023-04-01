package com.example.lepeenice.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val CustomLightColorPalette = lightColors(
    primary = primary_COLOR,
    primaryVariant = primaryVariant_Color,
    secondary = secondary_Color,
    background = background_Color,
    surface = surface_Color,
    onPrimary = onPrimary_Color,
    onSecondary = onSecondary_Color,
    onBackground = onBackground_Color,
    onSurface = onSurface_Color
)

@SuppressLint("ConflictingOnColor")
private val CustomDarkColorPalette = darkColors(
    primary = primaryVariant_Color,
    primaryVariant = primary_COLOR,
    secondary = secondaryVariant_Color,
    background = background_Color,
    surface = surface_Color,
    onPrimary = onPrimary_Color,
    onSecondary = onSecondary_Color,
    onBackground = onBackground_Color,
    onSurface = onSurface_Color
)



@Composable
fun LEpeeNiceTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        CustomDarkColorPalette
    } else {
        CustomLightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}