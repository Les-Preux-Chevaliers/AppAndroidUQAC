package com.appcovizzi.lepeenice.UIDisplay

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.appcovizzi.lepeenice.MemoryClassPackage.GameManager
import com.appcovizzi.lepeenice.MemoryClassPackage.Player
import com.appcovizzi.lepeenice.MemoryClassPackage.Sword
import com.appcovizzi.lepeenice.R


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
                    Prefab.CustomTitre(content = name)
                    Spacer(modifier = Modifier.height(8.dp))
                    Prefab.CustomSousTitre(content = version)
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
        fun Shop(swords: List<Sword>, currentContext: Context) {
            var startIndex by remember { mutableStateOf(0) }
            //val endIndex = minOf(startIndex + 5, swords.size)
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    //.background(MaterialTheme.colors.background.copy(alpha = 0.2f))
            ) {
                for (i in startIndex until swords.size) {
                    val sword = swords[i]
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clip(shape = RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colors.background)
                    ) {
                        Image(
                            painter = painterResource(com.appcovizzi.lepeenice.R.drawable.box),
                            contentDescription = null,
                            modifier = Modifier.height(80.dp),
                            contentScale = ContentScale.Crop,
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = sword.imageId),
                                contentDescription = sword.name,
                                modifier = Modifier
                                    .size(80.dp)
                                    //.background(MaterialTheme.colors.secondary)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(Modifier.weight(1f)) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = sword.name,
                                    color = Color.White,
                                    style = MaterialTheme.typography.body1.copy(
                                        fontSize = 14.sp,
                                        shadow = Shadow(
                                            color = Color.Black,
                                            offset = Offset(-2.0f, -2.0f),
                                            blurRadius = 1.0f
                                        )
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Button(
                                        onClick = {
                                            GameManager.getInstance()
                                                .onSwordClick(sword, currentContext)
                                            startIndex = swords.size//findInterval(i)
                                            startIndex = 0

                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            if (sword.isPurchased && sword.name == Player.getInstance().sword.name) {
                                                MaterialTheme.colors.primary
                                            } else if (sword.isPurchased) {
                                                MaterialTheme.colors.primaryVariant
                                            } else {
                                                MaterialTheme.colors.secondary
                                            }
                                        ),
                                        modifier = Modifier.padding(end = 8.dp)
                                    ) {
                                        Text(
                                            text = if (sword.isPurchased && sword.name == Player.getInstance().sword.name) {
                                                "Equipé"
                                            } else if (sword.isPurchased) {
                                                "Equiper"
                                            } else {
                                                "Acheter ${sword.price} CAD"
                                            },
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
                }
//                Row(
//                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.Bottom,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Box(modifier = Modifier.weight(1f)) {}
//                    Row(
//                        horizontalArrangement = Arrangement.spacedBy(8.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        if (startIndex > 0) {
//                            Button(
//                                onClick = {
//                                    startIndex -= 5
//                                    GameManager.getInstance().swords // Actualiser la liste de swords après un clic
//                                },
//                            ) {
//                                Text(text = "Précédent")
//                            }
//                        }
//                        if (endIndex < swords.size) {
//                            Button(
//                                onClick = {
//                                    startIndex += 5
//                                    GameManager.getInstance().swords // Actualiser la liste de swords après un clic
//                                },
//                            ) {
//                                Text(text = "Suivant")
//                            }
//                        }
//                    }
//                    Box(modifier = Modifier.weight(1f)) {}
//                }
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
            val levelneed = levelneed * 100

            var levelPercentage = niveau.toFloat() / levelneed.toFloat()
            if(levelPercentage > 1.0f){levelPercentage=1.0f}

            LinearProgressIndicator(
                progress = levelPercentage,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                color = MaterialTheme.colors.primaryVariant
            )
        }


        /*
        Ui pour les 3 points de vies
         */
        @Composable
        fun Shields(count: Int) {
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 0 until count) {
                    Image(
                        painter = painterResource(id = R.drawable.shield),
                        contentDescription = "Shilds $i",
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                    )
                    if (i != count - 1) {
                        Spacer(modifier = Modifier.width(30.dp))
                    }
                }
            }
        }

        fun findInterval(value: Int): Int {
            return (value / 5) * 5
        }


        @Composable
        fun PopUpTutorial(
            title: String,
            text: String,
            gifResource: Int,
            onClose: () -> Unit,
            onChange: () -> Unit
        ) {
            Dialog(
                onDismissRequest = onClose
            ) {
                Surface(
                    modifier = Modifier
                        .width(450.dp)
                        .wrapContentHeight()
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Titre
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // GIF
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            val imageLoader = ImageLoader.Builder(LocalContext.current)
                                .components {
                                    if (SDK_INT >= 28) {
                                        add(ImageDecoderDecoder.Factory())
                                    } else {
                                        add(GifDecoder.Factory())
                                    }
                                }
                                .build()

                            Image(
                                painter = rememberAsyncImagePainter(gifResource, imageLoader),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )

                        }

                        // Texte avec scroll
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            item {
                                Text(
                                    text = text,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
                                )
                            }
                        }

                        // Boutons
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .padding(horizontal = 16.dp) // Ajouter un padding horizontal de 16 dp
                                .wrapContentWidth(align = Alignment.CenterHorizontally) // Centrer les boutons horrizontalement
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(IntrinsicSize.Min)
                            ) {
                                Button(
                                    onClick = onChange,
                                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primaryVariant),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(IntrinsicSize.Min)
                                        .padding(8.dp),
                                    content = {
                                        Text(
                                            text = "Tutoriel suivant",
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = onClose,
                                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primaryVariant),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(IntrinsicSize.Min)
                                        .padding(8.dp),
                                    content = {
                                        Text(
                                            text = "Fermer",
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                )
                            }

                        }

                    }
                }
            }
        }

        @Composable
        fun PopUpCreadit(
            title: String,
            text: String,
            gifResource: Int,
            onClose: () -> Unit
        ) {
            Dialog(
                onDismissRequest = onClose
            ) {
                Surface(
                    modifier = Modifier
                        .width(450.dp)
                        .wrapContentHeight()
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Titre
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // GIF
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            val imageLoader = ImageLoader.Builder(LocalContext.current)
                                .components {
                                    if (SDK_INT >= 28) {
                                        add(ImageDecoderDecoder.Factory())
                                    } else {
                                        add(GifDecoder.Factory())
                                    }
                                }
                                .build()

                            Image(
                                painter = rememberAsyncImagePainter(gifResource, imageLoader),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )

                        }

                        // Texte avec scroll
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            item {
                                Text(
                                    text = text,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
                                )
                            }
                        }

                        // Boutons
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .padding(horizontal = 16.dp) // Ajouter un padding horizontal de 16 dp
                                .wrapContentWidth(align = Alignment.CenterHorizontally) // Centrer les boutons horrizontalement
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(IntrinsicSize.Min)
                            ) {
                                Button(
                                    onClick = onClose,
                                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primaryVariant),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(IntrinsicSize.Min)
                                        .padding(8.dp),
                                    content = {
                                        Text(
                                            text = "Fermer",
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                )
                            }

                        }

                    }
                }
            }
        }


    }
}