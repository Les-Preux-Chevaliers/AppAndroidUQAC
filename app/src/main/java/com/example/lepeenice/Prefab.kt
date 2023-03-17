package com.example.lepeenice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lepeenice.ui.theme.CustomBlanc
import com.example.lepeenice.ui.theme.CustomBleu

class Prefab {
    companion object {

        /*
        - Prefab de Texte -
         */
        @Composable
        fun CustomTitre(
            Content : String
        ) {
            Text(text = Content,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body1)
        }

        @Composable
        fun CustomSousTitre(
            Content : String
        ) {
            Text(text = Content,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.body2)
        }

        /*
        - Prefab de Image -
         */

        @Composable
        fun CustomImage(
            source: Int, contentDescription: String
        ) {
            Image(
                painter = painterResource(source),
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.primary, CircleShape)
            )
        }


        /*
        - Prefab de Bouton -
         */

        @Composable
        fun CustomBouton(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier = Modifier,
            backgroundColor: Color? = null,
            contentColor: Color = CustomBlanc
        ) {
            Button(
                onClick = onClick,
                modifier = modifier.background(backgroundColor ?: CustomBleu),
                colors = ButtonDefaults.buttonColors(backgroundColor ?: CustomBleu),
                content = {
                    Text(
                        text = text,
                        color = contentColor
                    )
                }
            )
        }


    }
}