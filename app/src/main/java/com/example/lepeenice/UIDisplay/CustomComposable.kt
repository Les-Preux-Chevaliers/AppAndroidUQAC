package com.example.lepeenice.UIDisplay

import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lepeenice.MemoryClassPackage.GameManager
import com.example.lepeenice.MemoryClassPackage.Player
import com.example.lepeenice.MemoryClassPackage.Sword
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
                        text = "MainUI",
                        onClick = { navController.navigate("MainUI") },
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
            } else if (currentLife < 0) {
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

        /*
    UI pour les éléments du shop
     */
        @Composable
        fun Shop(swords: List<Sword>) {
            var startIndex by remember { mutableStateOf(0) }
            val endIndex = minOf(startIndex + 5, swords.size)

            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colors.background.copy(alpha = 0.2f))
            ) {
                for (i in startIndex until endIndex) {
                    var sword = swords[i]
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(MaterialTheme.colors.surface)
                    ) {
                        Image(
                            painter = painterResource(id = sword.imageId),
                            contentDescription = sword.name,
                            modifier = Modifier
                                .size(80.dp)
                                .background(MaterialTheme.colors.surface)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(Modifier.weight(1f)) {
                            Text(
                                text = sword.name,
                                style = MaterialTheme.typography.h6,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Button(
                                    onClick = {
                                        GameManager.getInstance().onSwordClick(sword)
                                        startIndex = 1
                                        startIndex =
                                            0 // Actualiser la liste de swords après un clic
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        if (sword.isPurchased && sword == Player.getInstance().sword) {
                                            MaterialTheme.colors.primaryVariant
                                        } else if (sword.isPurchased) {
                                            MaterialTheme.colors.primary
                                        } else {
                                            MaterialTheme.colors.secondary
                                        }
                                    ),
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(
                                        text = if (sword.isPurchased) "Equipé" else "Acheter ${sword.price}CAD",
                                        style = MaterialTheme.typography.button
                                    )
                                }
                                Text(
                                    text = "Damage: ${sword.damage}",
                                    style = MaterialTheme.typography.body1,
                                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.6f),
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .align(Alignment.CenterVertically)
                                )
                            }
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier.weight(1f)) {}
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (startIndex > 0) {
                            Button(
                                onClick = {
                                    startIndex -= 5
                                    GameManager.getInstance().swords // Actualiser la liste de swords après un clic
                                },
                            ) {
                                Text(text = "Précédent")
                            }
                        }
                        if (endIndex < swords.size) {
                            Button(
                                onClick = {
                                    startIndex += 5
                                    GameManager.getInstance().swords // Actualiser la liste de swords après un clic
                                },
                            ) {
                                Text(text = "Suivant")
                            }
                        }
                    }
                    Box(modifier = Modifier.weight(1f)) {}
                }
            }
        }

        /*
        UI épée pouvant bouger en fonction du combat
         */
        @Composable
        fun MirroredImage(image: Painter, isMirrored: Boolean) {
            val scaleX = if (isMirrored) -1f else 1f
            val scaleY = 1f
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .scale(scaleX, scaleY)
                    .fillMaxSize()
            )
        }


        /*
    UI pour la bar de level du joueur
     */
        @Composable
        fun LevelBar(niveau: Int, levelneed: Int) {
            var levelneed = levelneed*100

            val levelPercentage = niveau.toFloat() / levelneed.toFloat()

            LinearProgressIndicator(
                progress = levelPercentage,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                color = MaterialTheme.colors.primaryVariant
            )
        }

    }
}