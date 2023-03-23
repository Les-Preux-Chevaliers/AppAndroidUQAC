package com.example.lepeenice

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lepeenice.ui.theme.LEpeeNiceTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LEpeeNiceTheme {
                val navController = rememberNavController()

                val items = listOf(
                    Screen.Home,
                    Screen.Shop,
                )

                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            items.forEach { screen ->
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            screen.icon,
                                            contentDescription = null
                                        )
                                    },
                                    label = { Text(stringResource(screen.resourceId)) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable("Home") {
                            CustomComposable.FirstHome(
                                name = "Mode App",
                                version = "Version : 0.0.2",
                                navController
                            )
                        }
                        composable("VibroPreview") { VibroClass.VibroPreview() }
                        composable("GyroPreview") { GyroClass.GyroPreview() }
                        composable("MemoryPreview") { MemoryClass.MemoryPreview() }
                        composable("SplashScreen") { SplashScreen.SplashScreen(navController) }
                        composable("TestUI") { DefaultPreview() }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : Screen("Home", R.string.home, Icons.Filled.Home)
    object Shop : Screen("SplashScreen", R.string.shop, Icons.Filled.ShoppingCart)
}


@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun DefaultPreview() {
    val currentContext: Context = LocalContext.current
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
                )
        ) {
            Column() {
                var Life by remember { mutableStateOf(70) }
                /*
            Score display code UI
             */

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = MaterialTheme.colors.background)
                        .padding(start = 20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterStart)
                    ) {
                        Prefab.CustomTitre(content = "Score : ")
                        /*
                        Texte pour le score à modifier lors du jeu
                         */
                        Prefab.CustomTitre(content = "666 888")
                        Prefab.CustomTitre(content = " points")
                    }
                }

                Box(
                    modifier = Modifier
                        .wrapContentHeight()
                        .align(Alignment.CenterHorizontally)
                ) {
                    /*
                    Image du monstre
                     */
                    Prefab.CustomImage(
                        source = R.drawable.mob1,
                        contentDescription = "MobPicture",
                        size = 240.dp,
                    )
                }
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .align(Alignment.CenterHorizontally)
                ) {

                    CustomComposable.LifeBar(currentLife = Life, maxLife = 100)

                    Box(modifier = Modifier.align(Alignment.Center)) {
                        Prefab.CustomTitre(content = "Gigachiax")
                    }
                }
                /*
                Bouton pour tester la bar de vie volatile
                 */
                Row {
                    Prefab.CustomButton(text = "+10", onClick = {
                        Life += 10
                        PlaySound.playSound(currentContext, R.raw.sword_metal_woosh, false)
                    })
                    Prefab.CustomButton(text = "-10", onClick = {
                        Life -= 10
                        PlaySound.playSound(currentContext, R.raw.sword_metal_woosh, false)
                    })
                }
            }


            //Show version, dont remove this on the preview !
            Box(modifier = Modifier.align(Alignment.BottomStart)) {
                CustomComposable.ModeDisplay(name = "Preview Mode", version = "Version : 0.0.2")
            }
        }
    }
}




