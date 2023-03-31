package com.example.lepeenice.UIDisplay

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lepeenice.R

class CustomComposable {
    companion object {
        /*
        Affiche la version de l'application (preview ou app).
         */
        @Composable
        fun ModeDisplay(name: String, version: String) {
            Prefab.CustomSurface {
                Column(modifier = Modifier.padding(all = 8.dp)) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Prefab.CustomTitre(content = "$name")
                    Spacer(modifier = Modifier.height(8.dp))
                    Prefab.CustomSousTitre(content = "$version")
                }
            }
        }

        /*
        Premier Home permettant le redirection entre les pages de test.
         */
        @Composable
        fun FirstHome(name: String, version: String, navController: NavController) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.padding(all = 8.dp)) {
                    Prefab.CustomSurface(
                        color = MaterialTheme.colors.primary,
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Prefab.CustomImage(
                            source = R.drawable.logoepeenice,
                            contentDescription = "LEpeeNiceLogo",
                            size = 240.dp,
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Prefab.CustomButton(
                        text = "Vibro Preview",
                        onClick = { navController.navigate("VibroPreview") },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Prefab.CustomButton(
                        text = "Gyro Preview",
                        onClick = { navController.navigate("GyroPreview") },
                        shape = RoundedCornerShape(32.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Prefab.CustomButton(
                        text = "Memory Preview",
                        onClick = { navController.navigate("MemoryPreview") },
                        shape = RoundedCornerShape(32.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Prefab.CustomButton(
                        text = "TestUI",
                        onClick = { navController.navigate("TestUI") },
                        shape = RoundedCornerShape(0.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Prefab.CustomButton(
                        text = "Splash Screen",
                        onClick = { navController.navigate("SplashScreen") },
                        shape = RoundedCornerShape(0.dp)
                    )

                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Prefab.CustomSurface {
                    ModeDisplay(name, version)
                }
            }
        }


        /*
    UI pour la bar de vie des monstres
     */
        @Composable
        fun LifeBar(currentLife: Int, maxLife: Int) {
            var life = currentLife
            if (life > maxLife) {
                life = maxLife
            }else if(currentLife < 0) {
                life = 0
            }

            val lifePercentage = life.toFloat() / maxLife.toFloat()

            LinearProgressIndicator(
                progress = lifePercentage,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                color = if (lifePercentage > 0.5f) MaterialTheme.colors.surface else MaterialTheme.colors.error
            )
        }


    }
}