package com.example.lepeenice.UIDisplay

import android.content.Context
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
import com.example.lepeenice.MemoryClassPackage.GameManager
import com.example.lepeenice.MemoryClassPackage.Player
import com.example.lepeenice.MemoryClassPackage.SaveManager
import com.example.lepeenice.SensorsUtilityClass
import com.example.lepeenice.ui.theme.LEpeeNiceTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class MainScreen {
    companion object {
        var Life = mutableStateOf(GameManager.getInstance().currentMonsterLife)


        @Composable
        fun MainScreen(sensor: SensorsUtilityClass) {
            val currentContext: Context = LocalContext.current
            sensor.useAccelerometer(currentContext)
            sensor.isOnCombatScreen = true

            LEpeeNiceTheme {
                val EncodedPlayer = Json.encodeToString(Player.getInstance())
                SaveManager.getInstance()
                    .saveDataToSharedPreferences_Player(currentContext, EncodedPlayer)
                val EncodedGameManager = Json.encodeToString(GameManager.getInstance())
                SaveManager.getInstance()
                    .saveDataToSharedPreferences_GameManager(currentContext, EncodedGameManager)

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
                        painter = painterResource(com.example.lepeenice.R.drawable.mainbackground),
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
                                        .wrapContentWidth()
                                        .wrapContentHeight()
                                        .padding(start = 20.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .border(
                                            3.dp,
                                            MaterialTheme.colors.secondary,
                                            RoundedCornerShape(10.dp)
                                        ),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start,
                                ) {
                                    Image(
                                        painter = painterResource(com.example.lepeenice.R.drawable.pieccesssesseseeses),
                                        contentDescription = "MoneyLogo",
                                        modifier = Modifier
                                            .height(50.dp)
                                            .width(50.dp)
                                            .padding(vertical = 8.dp)
                                    )
                                    Row(
                                        modifier = Modifier
                                            .padding(end = 12.dp)
                                    )
                                    {
                                        Prefab.CustomTitre(content = money.toString())
                                        Prefab.CustomTitre(content = " CAD")
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .width(250.dp)
                                        .padding(end = 50.dp)
                                        .align(Alignment.CenterEnd)
                                ) {
                                    CustomComposable.LevelBar(CurrentXp, Level)
                                    Box(modifier = Modifier.align(Alignment.Center)) {
                                        Prefab.CustomTitre(content = "Level $Level")
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .width(50.dp)
                                        .align(Alignment.CenterEnd)
                                ) {
                                    IconButton(
                                        onClick = { GameManager.getInstance().needFirstTutoriel = true },
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


                        /*
                        PopUp Tutoriel
                         */
                        /*
                        Pour combattre des monstres, c'est très simple.
                        Il vous suffit de prendre votre smartphone en mains comme si celui-ci était le manche d'une épée et de réaliser un grand mouvement avec votre bras comme si vous vouliez trancher quelque chose devant vous.
                        Si votre attaque est réussie, vous allez ressentir une vibration et entendre le bruit de votre épée qui frappe le monstre.
                        Bravo vous êtes fin prêt à terrasser des monstres, maintenant à vous de jouer !
                         */
                        if (GameManager.getInstance().needFirstTutoriel) {
                            CustomComposable.PopUpTutorial(
                                title = "Comment combatre ?",
                                text = "Pour combattre des monstres, c'est très simple." +
                                        "\n" +
                                        "Il vous suffit de prendre votre smartphone en mains comme si celui-ci était le manche d'une épée et de réaliser un grand mouvement avec votre bras comme si vous vouliez trancher quelque chose devant vous.\n" +
                                        "\n" +
                                        "Si votre attaque est réussie, vous allez ressentir une vibration et entendre le bruit de votre épée qui frappe le monstre.\n" +
                                        "\n" +
                                        "Bravo vous êtes fin prêt à terrasser des monstres, maintenant à vous de jouer !\n",
                                gifResource = com.example.lepeenice.R.raw.attaque,
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
                                text = "Pour se défendre des attaques de monstres, c'est très simple." +
                                        "\n" +
                                        "Il vous suffit de prendre votre smartphone en mains comme si celui-ci était le manche d'une épée et de ne pas bouger toute la durée de la vibration d'indication de l'attaque..\n" +
                                        "\n" +
                                        "Si votre défense est réussie, vous allez ressentir une vibration et entendre le bruit de bouclier frappé par le monstre.\n" +
                                        "\n" +
                                        "Sinon si c'est un échec, vous allez ressentir une vibration et entendre le bruit de votre corps frappé par le monstre.\n" +
                                        "\n" +
                                        "Bravo vous êtes fin prêt à vous défendre des monstres, maintenant à vous de jouer !\n",
                                gifResource = com.example.lepeenice.R.drawable.parade,
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