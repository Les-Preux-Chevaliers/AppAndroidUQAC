package com.example.lepeenice

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lepeenice.ui.theme.LEpeeNiceTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LEpeeNiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ModeDisplay("Mode App!")
                }
            }
        }
    }
}

@Composable
fun ModeDisplay(name: String) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Surface(color = MaterialTheme.colors.primary,
            shape = MaterialTheme.shapes.medium
        ) {
            Prefab.CustomImage(source = R.drawable.logoepeenice, contentDescription = "LEpeeNiceLogo")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Spacer(modifier = Modifier.height(4.dp))
            Prefab.CustomTitre(Content = "$name")
            Spacer(modifier = Modifier.height(8.dp))
            Prefab.CustomSousTitre(Content = "Version : 0.0.1")
            Prefab.CustomBouton(
                text = "Cliquez-moi",
                onClick = { /* Votre fonction de clic ici */ }
            )
        }
    }
}
@Preview(name = "Light Mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode")
@Composable
fun DefaultPreview() {
    LEpeeNiceTheme {
        Surface(modifier = Modifier.fillMaxSize()){
            ModeDisplay("Mode Preview")
        }
    }
}


//Zone de Alex
@Preview(name = "GyroPreview",showBackground = true)
@Composable
fun GyroPreview() {
    LEpeeNiceTheme {
        Surface(modifier = Modifier.fillMaxSize()){
            Text(text = "Version : 0.0.1")
        }
    }
}

//Zone de Gabin
@Preview(name = "VibroPreview",showBackground = true)
@Composable
fun VibroPreview() {
    val currentContext: Context = LocalContext.current
    LEpeeNiceTheme {
        Surface(modifier = Modifier.fillMaxSize()){
            Column {
                Text(text = "Version : 0.0.1")

                Button(onClick =
                {
                    Vibrate.vibratePhone(currentContext, 500)
                    PlaySound.playSound(currentContext, R.raw.sword_metal_woosh, false)
                })
                {
                    Text(text = "test vibration + sound")
                }
            }
        }
    }
}