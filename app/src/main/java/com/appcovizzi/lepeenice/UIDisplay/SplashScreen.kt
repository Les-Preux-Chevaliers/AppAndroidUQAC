package com.appcovizzi.lepeenice.UIDisplay

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.appcovizzi.lepeenice.MemoryClassPackage.SaveManager
import com.appcovizzi.lepeenice.R
import com.appcovizzi.lepeenice.SensorsUtilityClass

class SplashScreen {
    companion object {
        private const val REQUEST_BODY_SENSORS_PERMISSION = 1

        @Composable
        fun SplashScreen(navController: NavController, sensor: SensorsUtilityClass) {
            val currentContext: Context = LocalContext.current
            sensor.useAccelerometer(currentContext)
            sensor.unuseAccelerometer()
            sensor.isOnCombatScreen = false
            val hasPermissionsensor = ContextCompat.checkSelfPermission(
                currentContext,
                Manifest.permission.BODY_SENSORS
            ) == PackageManager.PERMISSION_GRANTED

            if (!hasPermissionsensor) {
                // L'autorisation d'accéder aux capteurs n'est pas encore accordée, donc on demande la permission à l'utilisateur
                ActivityCompat.requestPermissions(
                    currentContext as Activity,
                    arrayOf(Manifest.permission.BODY_SENSORS),
                    SplashScreen.REQUEST_BODY_SENSORS_PERMISSION
                )
                Toast.makeText(
                    currentContext,
                    "Accès aux capteurs non autorisé",
                    Toast.LENGTH_SHORT
                ).show()
            }




            SaveManager.getInstance().loadDataFromSharedPreferences(LocalContext.current)
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
                Image(
                    painter = painterResource(R.drawable.splashbackground),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
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
                            onClick = { navController.navigate("MainUI") }
                        )
                    }
                }
            }
        }
    }
}