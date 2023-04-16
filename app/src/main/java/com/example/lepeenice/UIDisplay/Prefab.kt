package com.example.lepeenice.UIDisplay

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
            Text(
                text = content,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.body1
            )

        }

        @Composable
        fun CustomSousTitre(
            content : String
        ) {
            Text(text = content,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.body2
            )
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
            shape: Shape = CircleShape
        ) {
            Image(
                painter = painterResource(source),
                contentDescription = contentDescription,
                modifier = modifier
                    .size(size)
                    .clip(shape)
            )
        }

        @Composable
        fun CustomImageWithBorder(
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

        @Composable
        fun CustomIconButtonWithText(
            text: String,
            icon: Painter,
            size: Dp = 48.dp,
            onClick: () -> Unit
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                shape = CircleShape,
                modifier = Modifier
                    .size(size)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = "Icon",
                        modifier = Modifier.size(size * 0.7f),
                        tint = MaterialTheme.colors.onPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = text,
                        style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }

        @Composable
        fun CustomIconButton(
            icon: Painter,
            size: Dp = 48.dp,
            iconSize: Dp = 24.dp,
            contentDescription: String? = null,
            onClick: () -> Unit = {},
            enabled: Boolean = true,
            modifier: Modifier = Modifier,
            tint: Color = MaterialTheme.colors.primary
        ) {
            IconButton(
                onClick = onClick,
                enabled = enabled,
                modifier = modifier.size(size),
                content = {
                    Icon(
                        painter = icon,
                        contentDescription = contentDescription,
                        modifier = Modifier
                            .size(iconSize),
                        tint = tint
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


