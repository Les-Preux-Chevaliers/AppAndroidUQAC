package com.example.lepeenice

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

class CustomComposable {
    companion object {
        /*
        Affiche la version de l'application (preview ou app).
         */
        @Composable
        fun ModeDisplay(name: String) {
                    Column(modifier = Modifier.padding(all = 8.dp)) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Prefab.CustomTitre(content = "$name")
                        Spacer(modifier = Modifier.height(8.dp))
                        Prefab.CustomSousTitre(content = "Version : 0.0.1")
                    }
        }

        /*
        Premier Home permettant le redirection entre les pages de test.
         */
        @Composable
        fun FirstHome(name : String,navController: NavController) {
            Column {
                Row(modifier = Modifier.padding(all = 8.dp)) {
                    Prefab.CustomSurface(
                        color = MaterialTheme.colors.primary,
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Prefab.CustomImage(
                            source = R.drawable.logoepeenice,
                            contentDescription = "LEpeeNiceLogo"
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Row(modifier = Modifier.padding(all = 8.dp),) {
                        Prefab.CustomButton(
                            text = "Vibro Preview",
                            onClick = { navController.navigate("VibroPreview") }
                        )
                        Prefab.CustomButton(
                            text = "Gyro Preview",
                            onClick = { navController.navigate("GyroPreview") },
                            shape = RoundedCornerShape(32.dp)
                        )
                        Prefab.CustomButton(
                            text = "TestUI",
                            onClick = { navController.navigate("TestUI") },
                            shape = RoundedCornerShape(0.dp)
                        )
                    }
                }
            }
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start){
                Prefab.CustomSurface {
                    CustomComposable.ModeDisplay(name)
                }
            }
        }
    }
}