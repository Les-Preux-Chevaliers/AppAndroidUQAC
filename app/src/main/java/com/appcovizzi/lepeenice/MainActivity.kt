package com.appcovizzi.lepeenice

import android.Manifest
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
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
import androidx.core.content.ContextCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.appcovizzi.lepeenice.MemoryClassPackage.SaveManager
import com.appcovizzi.lepeenice.UIDisplay.*
import com.appcovizzi.lepeenice.ui.theme.LEpeeNiceTheme
import kotlinx.serialization.json.Json


class MainActivity : ComponentActivity() {
    val Accelerometer = SensorsUtilityClass()
    var currentContext: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Json { isLenient = true; ignoreUnknownKeys = true }

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
        if (currentContext?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.BODY_SENSORS
                )
            } == PackageManager.PERMISSION_GRANTED
        ) {
            // L'autorisation d'accéder aux capteurs est accordée
//            println("activate accelerometer")
            if (Accelerometer.isOnCombatScreen == true) {
                Accelerometer.useAccelerometer(currentContext!!)
            }
        }
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : Screen("MainUI", R.string.home, Icons.Filled.PlayArrow)
    object Shop : Screen("Shop", R.string.shop, Icons.Filled.ShoppingCart)
    object Param : Screen("Param", R.string.param, Icons.Filled.Settings)
}


