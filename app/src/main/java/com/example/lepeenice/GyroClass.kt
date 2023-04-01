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

class GyroClass {
    companion object {
        val accelerometer = SensorsUtilityClass()

        //Zone de Alex
        @Composable
        fun GyroPreview(context: Context) {
            LEpeeNiceTheme {
                Surface(modifier = Modifier.fillMaxSize()){
                    Column() {
                        Text(text = "Gyro Preview")
                        Text(text = "Version : 0.0.1")
                    }
                }
            }
            accelerometer.useAccelerometer(context)

            //Start bitcoin minor ^^
            //accelerometer.unuseAccelerometer()
        }
    }
}