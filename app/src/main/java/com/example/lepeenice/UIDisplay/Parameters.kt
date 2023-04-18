package com.example.lepeenice.UIDisplay

import android.content.Context
import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
                        val sensibilityString =
                            String.format("%.1f", Player.getInstance().sensibility)
                        Text(text = "Sensibilité : " + sensibilityString)
                        Slider(
                            value = GameManager.getInstance().sensibility,
                            onValueChange = {
                                GameManager.getInstance().sensibility = it
                                Player.getInstance().sensibility = it
                                val EncodedPlayer = Json.encodeToString(Player.getInstance())
                                SaveManager.getInstance().saveDataToSharedPreferences_Player(
                                    currentContext,
                                    EncodedPlayer
                                )
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

                        /*
                        Crédit
                         */
                        Spacer(modifier = Modifier.padding(vertical = 20.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                onClick = {
                                    GameManager.getInstance().creaditTutorial = true
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = MaterialTheme.colors.primaryVariant
                                ),
                            ){
                                Text(
                                    text = "Crédits",
                                )
                            }
                        }


                    }
                }
                if (GameManager.getInstance().creaditTutorial) {
                    CustomComposable.PopUpCreadit(
                        title = "Qui sommes-nous ?",
                        text = "Étudiants à l'université du Québec à Chicoutimi.\n" +
                                "\n" +
                                "Nous avons réalisé cette application en 2 mois lors de notre cours d'informatique Mobile. Notre équipe est constituée de 5 preux chevaliers avec pour objectif de repousser les limites de l'interactivité des applications mobiles.\n" +
                                "\n" +
                                "Les 5 preux chevaliers : \n" +
                                "\n" +
                                "Alex Jurbert : Mage noir de la technique et des capteurs, maîtrise l'accélération des téléphones à la perfection.\n" +
                                "\n" +
                                "Cyrielle Bracher : Barde graphiste et conceptrice UI, avec son stylo elle est capable de créer de la vie.\n" +
                                "\n" +
                                "Julien Pirat : Sage de la mémoire et des sauvegardes, sans lui, l'application n'aurait aucun souvenir.\n" +
                                "\n" +
                                "Gabin Demonet : Assassin de l'ombre et des feedback, si ton téléphone vibre, c'est de sa faute.\n" +
                                "\n" +
                                "Rémi Covizzi : Paladin du gameplay et de l'UI, assembleur d'élément graphique et manipulateur de données.\n" +
                                "\n" +
                                "Notre joyeuse bande espère que vous passerez un bon moment sur notre application !\n",
                        gifResource = com.example.lepeenice.R.raw.creaditslepeenice,
                        onClose = {
                            GameManager.getInstance().creaditTutorial = false
                        },
                    )
                }
            }
        }
    }
}