package com.example.lepeenice

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.lepeenice.ui.theme.LEpeeNiceTheme

class VibroClass {
    companion object {

        //Zone de Gabin
        @Preview(name = "VibroPreview", showBackground = true)
        @Composable
        fun VibroPreview() {
            val currentContext: Context = LocalContext.current
            LEpeeNiceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
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
    }
}