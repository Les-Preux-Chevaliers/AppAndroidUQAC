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
    }
}