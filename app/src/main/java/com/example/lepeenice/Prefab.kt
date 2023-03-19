package com.example.lepeenice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.lepeenice.ui.theme.CustomBlanc
import com.example.lepeenice.ui.theme.CustomBleu

class Prefab {
    companion object {

        /*
        - Prefab de Texte -
         */

        @Composable
        fun CustomText(
            text: String,
            modifier: Modifier = Modifier,
            fontWeight: FontWeight? = null,
            fontSize: TextUnit = TextUnit.Unspecified,
            textAlign: TextAlign? = null,
            color: Color = Color.Black,
            fontFamily: FontFamily = FontFamily.Default,
            letterSpacing: TextUnit = TextUnit.Unspecified,
            lineHeight: TextUnit = TextUnit.Unspecified
        ) {
            Text(
                text = text,
                modifier = modifier,
                fontWeight = fontWeight,
                fontSize = fontSize,
                textAlign = textAlign,
                color = color,
                fontFamily = fontFamily,
                letterSpacing = letterSpacing,
                lineHeight = lineHeight
            )
        }



        @Composable
        fun CustomTitre(
            content : String
        ) {
            Text(text = content,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body1)
        }

        @Composable
        fun CustomSousTitre(
            content : String
        ) {
            Text(text = content,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.body2)
        }

        /*
        - Prefab de Image -
         */

        @Composable
        fun CustomImage(
            source: Int,
            contentDescription: String,
            modifier: Modifier = Modifier,
            size: Dp = 60.dp,
            shape: Shape = CircleShape,
            borderWidth: Dp = 1.5.dp,
            borderColor: Color = MaterialTheme.colors.primary
        ) {
            Image(
                painter = painterResource(source),
                contentDescription = contentDescription,
                modifier = modifier
                    .size(size)
                    .clip(shape)
                    .border(borderWidth, borderColor, shape)
            )
        }



        /*
        - Prefab de Bouton -
         */

        @Composable
        fun CustomButton(
            text: String,
            onClick: () -> Unit,
            modifier: Modifier = Modifier,
            shape: Shape = RoundedCornerShape(16.dp),
            backgroundColor: Color = MaterialTheme.colors.primary,
            contentColor: Color = MaterialTheme.colors.onPrimary,
            contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            textStyle: TextStyle = MaterialTheme.typography.button
        ) {
            Button(
                onClick = onClick,
                modifier = modifier
                    .defaultMinSize(
                        minWidth = ButtonDefaults.MinWidth,
                        minHeight = ButtonDefaults.MinHeight
                    )
                    .background(backgroundColor, shape),
                shape = shape,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = backgroundColor,
                    contentColor = contentColor
                ),
                contentPadding = contentPadding,
                content = {
                    Text(
                        text = text,
                        color = contentColor,
                        style = textStyle,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }




        /*
        - Prefab de Surface -
         */

        @Composable
        fun CustomSurface(
            modifier: Modifier = Modifier,
            color: Color = MaterialTheme.colors.surface,
            contentColor: Color = contentColorFor(color),
            elevation: Dp = 4.dp,
            shape: Shape = MaterialTheme.shapes.medium,
            content: @Composable () -> Unit
        ) {
            Surface(
                modifier = modifier,
                color = color,
                contentColor = contentColor,
                elevation = elevation,
                shape = shape,
                content = content
            )
        }


    }
}