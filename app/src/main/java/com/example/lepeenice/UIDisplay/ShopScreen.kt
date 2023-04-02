package com.example.lepeenice.UIDisplay

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.lepeenice.MemoryClassPackage.GameManager
import com.example.lepeenice.MemoryClassPackage.Player
import com.example.lepeenice.ui.theme.LEpeeNiceTheme

class ShopScreen {
    companion object {
        @Composable
        fun ShopScreen() {
            val currentContext: Context = LocalContext.current
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
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .background(color = MaterialTheme.colors.background)
                                .padding(start = 20.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .align(Alignment.CenterStart)
                            ) {
                                Prefab.CustomTitre(content = "Money : ")
                                /*
                        Texte pour le score à modifier lors du jeu
                         */
                                Prefab.CustomTitre(content = money.toString())
                                Prefab.CustomTitre(content = " CAD")
                            }
                        }

                        /*
                    Ui pour tout les éléments du shop
                     */
                        val swords by remember { mutableStateOf(GameManager.getInstance().swords) }
                        CustomComposable.Shop(swords)
                    }
                    //Show version, dont remove this on the preview !
                    Box(modifier = Modifier.align(Alignment.BottomStart)) {
                        CustomComposable.ModeDisplay(name = "Preview Mode", version = "Version : 0.0.4")
                    }
                }

            }
        }
    }
}