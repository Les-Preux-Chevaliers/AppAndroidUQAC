package com.example.lepeenice.UIDisplay

import android.content.Context
import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lepeenice.MemoryClassPackage.GameManager
import com.example.lepeenice.MemoryClassPackage.Player
import com.example.lepeenice.MemoryClassPackage.SaveManager
import com.example.lepeenice.R
import com.example.lepeenice.SensorsUtilityClass
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Parameters {
    companion object {
        @Composable
        fun Parameters(sensor: SensorsUtilityClass) {
            val currentContext: Context = LocalContext.current
            sensor.useAccelerometer(currentContext)
            sensor.unuseAccelerometer()
            sensor.isOnCombatScreen = false
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
                    painter = painterResource(R.drawable.parambackground),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                Column(Modifier.padding(32.dp)) {
                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Prefab.CustomImage(
                            source = R.drawable.logoepeenice,
                            contentDescription = "LEpeeNiceLogo",
                            size = 240.dp
                        )
                    }

                    /*
                    Son
                     */

                    /*
                    Effet audio
                     */

                    /*
                    Vibration
                     */

                    /*
                    Sensibilité
                     */
                    Column() {
                        val sensibilityString = String.format("%.1f", Player.getInstance().sensibility)
                        Text(text = "Sensibilité : " + sensibilityString)
                        Slider(
                            value = GameManager.getInstance().sensibility,
                            onValueChange = {
                                GameManager.getInstance().sensibility = it
                                Player.getInstance().sensibility = it
                                val EncodedPlayer = Json.encodeToString(Player.getInstance())
                                SaveManager.getInstance().saveDataToSharedPreferences_Player(currentContext,EncodedPlayer)
//                                val EncodedGameManager = Json.encodeToString(GameManager.getInstance())
//                                SaveManager.getInstance().saveDataToSharedPreferences_GameManager(currentContext,EncodedGameManager)
                            },
                            valueRange = 7f..23f,
                            steps = 100,
                            modifier = Modifier
                                .height(32.dp)
                                .padding(horizontal = 32.dp),
                            colors = SliderDefaults.colors(
                                thumbColor = Color.Black,
                                activeTrackColor = Color.Blue,
                                inactiveTrackColor = Color.Gray
                            ),
                            onValueChangeFinished = { println("Value change finished") }
                        )
                        Button(
                            onClick = {
                                GameManager.getInstance().sensibility = 12.0f
                                Player.getInstance().sensibility = 12.0f
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (GameManager.getInstance().sensibility == 12.0f) {
                                    MaterialTheme.colors.primary
                                } else {
                                    MaterialTheme.colors.primaryVariant
                                }
                            )
                        ) {
                            Text(
                                text = "Par défault",
                            )
                        }

                    }
                }

            }
        }
    }
}