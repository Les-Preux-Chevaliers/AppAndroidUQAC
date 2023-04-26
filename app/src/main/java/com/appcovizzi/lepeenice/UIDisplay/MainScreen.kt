package com.appcovizzi.lepeenice.UIDisplay

import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.appcovizzi.lepeenice.MemoryClassPackage.GameManager
import com.appcovizzi.lepeenice.MemoryClassPackage.Player
import com.appcovizzi.lepeenice.MemoryClassPackage.SaveManager
import com.appcovizzi.lepeenice.SensorsUtilityClass
import com.appcovizzi.lepeenice.ui.theme.LEpeeNiceTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class MainScreen {
    companion object {
        var Life = mutableStateOf(GameManager.getInstance().currentMonsterLife)
        private const val REQUEST_BODY_SENSORS_PERMISSION = 1


        @Composable
        fun MainScreen(sensor: SensorsUtilityClass) {
            val currentContext: Context = LocalContext.current
            if (ContextCompat.checkSelfPermission(
                    currentContext,
                    Manifest.permission.BODY_SENSORS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // L'autorisation d'accéder aux capteurs est accordée
//                Toast.makeText(currentContext, "Activation du capteur", Toast.LENGTH_SHORT).show()
                sensor.useAccelerometer(currentContext)
//                println("activate accelerometer")
            } else {
                // L'autorisation d'accéder aux capteurs n'est pas encore accordée, donc on demande la permission à l'utilisateur
                ActivityCompat.requestPermissions(
                    currentContext as Activity,
                    arrayOf(Manifest.permission.BODY_SENSORS),
                    REQUEST_BODY_SENSORS_PERMISSION
                )
                Toast.makeText(
                    currentContext,
                    "Accès aux capteurs non autorisé",
                    Toast.LENGTH_SHORT
                ).show()
            }

            val EncodedPlayer:String = Json.encodeToString(Player.getInstance())
            SaveManager.getInstance().saveDataToSharedPreferences_Player(currentContext,EncodedPlayer)
            val EncodedGameManager:String = Json.encodeToString(GameManager.getInstance())
            SaveManager.getInstance().saveDataToSharedPreferences_GameManager(currentContext,EncodedGameManager)

            sensor.isOnCombatScreen = true

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
                    Image(
                        painter = painterResource(com.appcovizzi.lepeenice.R.drawable.mainbackground),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                    Column {
                        var Life = GameManager.getInstance().currentMonsterLife
                        GameManager.getInstance().currentMoney = Player.getInstance().getMoney()
                        var money = GameManager.getInstance().currentMoney
                        var Level = Player.getInstance().getLevel()
                        GameManager.getInstance().currentXp = Player.getInstance().getXp()
                        var CurrentXp = GameManager.getInstance().currentXp
                        /*
                Score display code UI
                 */
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .background(color = MaterialTheme.colors.surface)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .wrapContentWidth()
                                            .wrapContentHeight()
                                            .padding(start = 20.dp)
                                            .padding(end = 16.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .border(
                                                3.dp,
                                                MaterialTheme.colors.secondary,
                                                RoundedCornerShape(10.dp)
                                            ),
                                        verticalAlignment = Alignment.CenterVertically, // Ajout de l'alignement vertical
                                        horizontalArrangement = Arrangement.Start,
                                    ) {
                                        Image(
                                            painter = painterResource(com.appcovizzi.lepeenice.R.drawable.pieccesssesseseeses),
                                            contentDescription = "MoneyLogo",
                                            modifier = Modifier
                                                .height(50.dp)
                                                .width(50.dp)
                                                .padding(vertical = 8.dp)
                                        )
                                        Row(
                                            modifier = Modifier
                                                .padding(start = 12.dp, end = 12.dp),
                                            verticalAlignment = Alignment.CenterVertically // Ajout de l'alignement vertical
                                        ) {
                                            Prefab.CustomTitre(content = money.toString())
                                            Prefab.CustomTitre(
                                                content = " OR"
                                            )
                                        }
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Box(
                                            modifier = Modifier.weight(3f),
                                        ) {
                                            CustomComposable.LevelBar(CurrentXp, Level)
                                            Box(modifier = Modifier.align(Alignment.Center)) {
                                                Prefab.CustomTitre(content = "Level $Level")
                                            }
                                        }
                                        Box(
                                            modifier = Modifier.weight(1f),
                                        ) {
                                            IconButton(
                                                onClick = {
                                                    GameManager.getInstance().needFirstTutoriel =
                                                        true
                                                },
                                                modifier = Modifier.size(50.dp)
                                            ) {
                                                Icon(
                                                    Icons.Default.Info,
                                                    contentDescription = "Ouvrir la popup"
                                                )
                                            }
                                        }
                                    }
                                }

                            }
                        }


                        /*
                        PopUp Tutoriel
                         */
                        if (GameManager.getInstance().needFirstTutoriel) {
                            CustomComposable.PopUpTutorial(
                                title = "Comment combatre ?",
                                text = "Pour combattre les monstres, votre téléphone est votre épée !" +
                                        "\n" +
                                        "Utilisez votre téléphone comme si le monstre était devant vous.\n" +
                                        "\n" +
                                        "Privilégiez des mouvements amples.\n" +
                                        "\n" +
                                        "Pour chaque attaque réussite, vous obtiendrez une petite récompense, maintenant à vous de jouer !\n",
                                gifResource = com.appcovizzi.lepeenice.R.raw.attaque,
                                onClose = {
                                    Player.getInstance().needfirsttuto = false
                                    GameManager.getInstance().needFirstTutoriel = false
                                },
                                onChange = {
                                    GameManager.getInstance().needSecondTutoriel = true
                                    GameManager.getInstance().needFirstTutoriel = false
                                }
                            )
                        }
                        if (GameManager.getInstance().needSecondTutoriel) {
                            CustomComposable.PopUpTutorial(
                                title = "Comment se défendre ?",
                                text = "Lorsque le montre attaque, restez immobile avec votre téléphone devant vous." +
                                        "\n" +
                                        "Son attaque et caractérisée par un cri et une vibration prolongée.\n" +
                                        "\n" +
                                        "Si la parade est réussie, un son de bouclier sera émit.\n" +
                                        "Si c'est un échec, vous perdez une vie.\n" +
                                        "\n" +
                                        "Lorsque vous perdez toutes vos vies, le monstre actuel vous bat, et vous passez au monstre suivant sans récupérer les récompenses.\n" +
                                        "\n" +
                                        "Bon courage !\n",
                                gifResource = com.appcovizzi.lepeenice.R.drawable.parade,
                                onClose = {
                                    Player.getInstance().needsecondetuto = false
                                    GameManager.getInstance().needSecondTutoriel = false
                                },
                                onChange = {
                                    GameManager.getInstance().needFirstTutoriel = true
                                    GameManager.getInstance().needSecondTutoriel = false
                                }
                            )
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
                                source = if ((Life.toFloat() / GameManager.getInstance().currentMonster.hp.toFloat()) > 0.5f) {
                                    GameManager.getInstance().currentMonster.imageUri
                                } else {
                                    GameManager.getInstance().currentMonster.imageUri
                                },
                                contentDescription = "MobPicture",
                                size = 400.dp,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .width(300.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {

                            CustomComposable.LifeBar(
                                currentLife = Life,
                                maxLife = GameManager.getInstance().currentMonster.hp// * Player.getInstance().getLevel()
                            )

                            Box(modifier = Modifier.align(Alignment.Center)) {
                                var name = GameManager.getInstance().currentMonster.name
                                Prefab.CustomTitre(content = "$name : $Life hp")
                            }
                        }
                        /*
                    Bouton pour tester la bar de vie volatile
                     */
//                        Row {
//                            Prefab.CustomButton(text = "Attaque", onClick = {
//                                GameManager.getInstance().dealDamagestest()
//                                PlaySound.playSound(
//                                    currentContext,
//                                    com.example.lepeenice.R.raw.sword_metal_woosh,
//                                    false
//                                )
//                            })
//                            Prefab.CustomButton(text = "Defense", onClick = {
//                                GameManager.getInstance().MonsterAttack(currentContext)
//                                PlaySound.playSound(
//                                    currentContext,
//                                    com.example.lepeenice.R.raw.sword_metal_woosh,
//                                    false
//                                )
//                            })
//
//                        }
                        CustomComposable.Shields(count = GameManager.getInstance().currentShildNumber)
//                        timer(initialDelay = 0, period = 5000) {
//                            GameManager.getInstance().MonsterAttack()
//                            PlaySound.playSound(
//                                currentContext,
//                                com.example.lepeenice.R.raw.sword_metal_woosh,
//                                false
//                            )
//                        }


                        Box(
                            modifier = Modifier
                                .wrapContentHeight()
                                .align(Alignment.CenterHorizontally)
                        ) {
                            /*
                        Image de l'épée
                         */
                            CustomComposable.MirroredImage(
                                image = painterResource(Player.getInstance().sword.imageId),
                                isMirrored = GameManager.getInstance().currentBoolAttack
                            )
                        }
                    }


                    //Show version, dont remove this on the preview !
//                    Box(modifier = Modifier.align(Alignment.BottomStart)) {
//                        CustomComposable.ModeDisplay(
//                            name = "App Mode",
//                            version = "Version : 0.0.3"
//                        )
//                    }
                }
            }
        }
    }
}