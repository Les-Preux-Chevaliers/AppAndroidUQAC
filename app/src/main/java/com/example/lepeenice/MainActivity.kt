package com.example.lepeenice

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lepeenice.ui.theme.LEpeeNiceTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LEpeeNiceTheme {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Home") {
                        composable("Home") { CustomComposable.FirstHome(name = "Mode App",navController) }
                        composable("VibroPreview") { VibroClass.VibroPreview() }
                        composable("GyroPreview") { GyroClass.GyroPreview() }
                        composable("MemoryPreview") { MemoryClass.MemoryPreview() }
                        composable("SplashScreen") { SplashScreen.SplashScreen(navController) }
                        composable("TestUI") { CustomComposable.FirstHome(name = "Mode App",navController) }
                    }
            }
        }
    }
}



@Preview(name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode")
@Composable
fun DefaultPreview() {
    LEpeeNiceTheme {
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
                )) {
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
                        onClick = { /* Votre fonction de clic ici */ }
                    )
                }
            }




            //Show version, dont remove this on the preview !
            Box(modifier = Modifier.align(Alignment.BottomStart)){
                CustomComposable.ModeDisplay(name = "Preview Mode")
            }
        }
    }
}




