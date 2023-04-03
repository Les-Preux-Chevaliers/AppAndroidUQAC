package com.example.lepeenice.UIDisplay

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lepeenice.MemoryClassPackage.GameManager
import com.example.lepeenice.MemoryClassPackage.Player
import com.example.lepeenice.R
import com.example.lepeenice.SensorsUtilityClass
import com.example.lepeenice.ui.theme.LEpeeNiceTheme

class ShopScreen {
    companion object {
        @Composable
        fun ShopScreen(sensor : SensorsUtilityClass) {
            val currentContext: Context = LocalContext.current
            sensor.unuseAccelerometer()
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
                                .background(color = MaterialTheme.colors.background)
                        ) {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)) {
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
                                        painter = painterResource(R.drawable.pieccesssesseseeses),
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
                                        .width(200.dp)
                                        .padding(end = 20.dp)
                                        .align(Alignment.CenterEnd)
                                ) {
                                    CustomComposable.LevelBar(CurrentXp,Level)
                                    Box(modifier = Modifier.align(Alignment.Center)) {
                                        Prefab.CustomTitre(content = "Level $Level")
                                    }
                                }
                            }
                        }

                        /*
                    Ui pour tout les éléments du shop
                     */
                        val swords by remember { mutableStateOf(GameManager.getInstance().swords) }
                        CustomComposable.Shop(swords)
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