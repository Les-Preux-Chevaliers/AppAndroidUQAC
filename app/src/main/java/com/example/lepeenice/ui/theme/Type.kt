package com.example.lepeenice.ui.theme

import android.content.res.Resources.Theme
import androidx.compose.material.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily(Font(com.example.lepeenice.R.font.russoone)),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.1.em,
        shadow = Shadow(
            color = Color.Black,
            offset = Offset(-2.0f, -2.0f),
            blurRadius = 1.0f
        )
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(Font(com.example.lepeenice.R.font.russoone)),
        fontWeight = FontWeight.W900,
        fontSize = 8.sp,
        letterSpacing = 0.1.em,
    ),
    h6 = TextStyle(
        fontFamily = FontFamily(Font(com.example.lepeenice.R.font.russoone)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.1.em,
    ),
    button = TextStyle(
        fontFamily = FontFamily(Font(com.example.lepeenice.R.font.russoone)),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.1.em,
    ),


    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)