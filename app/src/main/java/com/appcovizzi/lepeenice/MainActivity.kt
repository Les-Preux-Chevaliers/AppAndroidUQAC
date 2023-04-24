package com.appcovizzi.lepeenice

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.appcovizzi.lepeenice.MemoryClassPackage.SaveManager
import com.appcovizzi.lepeenice.UIDisplay.*
import com.appcovizzi.lepeenice.ui.theme.LEpeeNiceTheme


class MainActivity : ComponentActivity() {
    val Accelerometer = SensorsUtilityClass()
    var currentContext: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            // Load les data si il y en a !
            SaveManager.getInstance().loadDataFromSharedPreferences(LocalContext.current)
            currentContext = LocalContext.current
            LEpeeNiceTheme {
                val navController = rememberNavController()

                val items = listOf(
                    Screen.Home,
                    Screen.Shop,
                    Screen.Param,
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
                        startDestination = "SplashScreen",
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
                        composable("SplashScreen") { SplashScreen.SplashScreen(navController, Accelerometer) }
                        composable("Shop") { ShopScreen.ShopScreen(Accelerometer)}
                        composable("MainUI") { MainScreen.MainScreen(Accelerometer)}
                        composable("Param") { Parameters.Parameters(sensor = Accelerometer)}
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Accelerometer.unuseAccelerometer()
    }

    override fun onResume() {
        super.onResume()
        if(Accelerometer.isOnCombatScreen == true) {
            Accelerometer.useAccelerometer(currentContext!!)
        }
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : Screen("MainUI", R.string.home, Icons.Filled.PlayArrow)
    object Shop : Screen("Shop", R.string.shop, Icons.Filled.ShoppingCart)
    object Param : Screen("Param", R.string.param, Icons.Filled.Settings)
}


//@Preview(name = "Light Mode")
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode"
//)
//@Composable
//fun DefaultPreview() {
//    val currentContext: Context = LocalContext.current
//    LEpeeNiceTheme {
//        Box(
//            Modifier
//                .fillMaxSize()
//                .background(
//                    brush = Brush.verticalGradient(
//                        colors = listOf(
//                            MaterialTheme.colors.secondary,
//                            MaterialTheme.colors.surface
//                        ),
//                        startY = 1000f,
//                        endY = 2000f
//                    )
//                )
//        ) {
//            Column() {
//                /*
//                Score display code UI
//                 */
//
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp)
//                        .background(color = MaterialTheme.colors.background)
//                        .padding(start = 20.dp)
//                ) {
//                    Row(
//                        modifier = Modifier
//                            .wrapContentSize()
//                            .align(Alignment.CenterStart)
//                    ) {
//                        Prefab.CustomTitre(content = "Money : ")
//                        /*
//                Texte pour le score à modifier lors du jeu
//                 */
//                        Prefab.CustomTitre(content = Player.getInstance().getMoney().toString())
//                        Prefab.CustomTitre(content = " CAD")
//                    }
//                }
//
//                /*
//            Ui pour tout les éléments du shop
//             */
//                CustomComposable.Shop(swords = GameManager.getInstance().swords)
//            }
//            //Show version, dont remove this on the preview !
//            Box(modifier = Modifier.align(Alignment.BottomStart)) {
//                CustomComposable.ModeDisplay(name = "Preview Mode", version = "Version : 0.0.4")
//            }
//        }
//    }
//}
//


