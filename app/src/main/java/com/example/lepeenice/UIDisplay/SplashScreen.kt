package com.example.lepeenice.UIDisplay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lepeenice.R

class SplashScreen {
    companion object {
        @Composable
        fun SplashScreen(navController: NavController) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colors.secondary,
                                MaterialTheme.colors.surface
                            ),
                            startY = 1000f,
                            endY = 2000f
                        )
                    )
            ) {
                Box(modifier = Modifier.align(Alignment.TopCenter)) {
                    Prefab.CustomImage(
                        source = R.drawable.logoepeenice,
                        contentDescription = "LEpeeNiceLogo",
                        size = 480.dp
                    )
                }
                Box(modifier = Modifier.align(Alignment.Center)) {
                    Column() {
                        Spacer(modifier = Modifier.height(320.dp))
                        Prefab.CustomIconButton(
                            icon = painterResource(id = R.drawable.iconplay),
                            iconSize = 120.dp,
                            size = 240.dp,
                            tint = MaterialTheme.colors.onBackground,
                            onClick = { navController.navigate("Home") }
                        )
                    }
                }
            }
        }
    }
}