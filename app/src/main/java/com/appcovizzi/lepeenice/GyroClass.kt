package com.appcovizzi.lepeenice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.appcovizzi.lepeenice.ui.theme.LEpeeNiceTheme

class GyroClass {
    companion object {
        //val accelerometer = SensorsUtilityClass()


        //Zone de Alex
        @Composable
        fun GyroPreview() {
            LEpeeNiceTheme {
                Surface(modifier = Modifier.fillMaxSize()){
                    Column() {
                        Text(text = "Gyro Preview")
                        Text(text = "Version : 0.0.1")
                    }
                }
            }
            //accelerometer.useAccelerometer(context)

            //Start bitcoin minor ^^
            //accelerometer.unuseAccelerometer()
        }
    }
}