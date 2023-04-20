package com.appcovizzi.lepeenice.UIDisplay

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.appcovizzi.lepeenice.MemoryClassPackage.GameManager
import com.appcovizzi.lepeenice.MemoryClassPackage.Player
import com.appcovizzi.lepeenice.SensorsUtilityClass
import com.appcovizzi.lepeenice.ui.theme.LEpeeNiceTheme

class ShopScreen {
    companion object {
        @Composable
        fun ShopScreen(sensor : SensorsUtilityClass) {
            val currentContext: Context = LocalContext.current
            sensor.unuseAccelerometer()
            sensor.isOnCombatScreen = false
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
                        painter = painterResource(com.appcovizzi.lepeenice.R.drawable.shopbackground),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                    Column() {
                        /*
                        Score display code UI
                         */
                        GameManager.getInstance().currentMoney = Player.getInstance().getMoney()
                        var money = GameManager.getInstance().currentMoney
                        var Level = Player.getInstance().getLevel()
                        GameManager.getInstance().currentXp = Player.getInstance().getXp()
                        var CurrentXp = GameManager.getInstance().currentXp
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
                                                content = " CAD"
                                            )
                                        }
                                    }
                                    Row(modifier = Modifier
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

                                        }
                                    }
                                }

                            }
                        }

                        /*
                    Ui pour tout les éléments du shop
                     */
                        val swords by remember { mutableStateOf(GameManager.getInstance().swords) }
                        CustomComposable.Shop(swords, currentContext)
                    }
//                    //Show version, dont remove this on the preview !
//                    Box(modifier = Modifier.align(Alignment.BottomStart)) {
//                        CustomComposable.ModeDisplay(name = "Preview Mode", version = "Version : 0.0.4")
//                    }
                }

            }
        }
    }
}